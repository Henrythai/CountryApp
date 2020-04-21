package com.example.countryapp.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.Model.Country
import com.example.countryapp.R
import com.example.countryapp.Util.loadUrl
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tvName = view.tvName
        private val tvCapital = view.tvCapital
        private val imgFlag = view.imgFlag
        fun bind(country: Country) {
            tvName.text = country.countryName
            tvCapital.text = country.capital
            imgFlag.loadUrl(country.flag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount() = countries.size


}