package com.example.countryapp.Util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.countryapp.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadUrl(
    url: String?,
    circularProgressDrawable: CircularProgressDrawable = getProgressDrawable(
        context
    )
) {
    url?.let {
        Glide.with(this)
            .load(url)
            .error(R.mipmap.ic_launcher_round)
            .placeholder(circularProgressDrawable)
            .into(this)
    }

}