package com.example.countryapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.App
import com.example.countryapp.R
import com.example.countryapp.ViewModel.MainActivityViewModel
import com.example.countryapp.ViewModel.MainActivityViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var viewmodel: MainActivityViewModel

    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory

    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.inject(this)

        viewmodel = ViewModelProvider(
            this,
            mainActivityViewModelFactory
        ).get(MainActivityViewModel::class.java)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewmodel.refresh()
        }

        rvCountry.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }
        viewmodel.refresh()
        observeViewmodel()

    }

    fun observeViewmodel() {
        viewmodel.countries.observe(this, Observer { countries ->
            countries?.let {
                if (!it.isEmpty()) {
                    countryAdapter.updateCountries(it)
                    rvCountry.visibility = View.VISIBLE
                }
            }
        })
        viewmodel.loadError.observe(this, Observer { isError ->
            isError?.let {
                tvErrors.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        viewmodel.loading.observe(this, Observer { isload ->
            isload?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                    rvCountry.visibility = View.GONE
                    tvErrors.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}
