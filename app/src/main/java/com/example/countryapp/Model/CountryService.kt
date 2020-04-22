package com.example.countryapp.Model


import com.example.countryapp.App
import com.example.countryapp.Di.DaggerCountryComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CountryService @Inject constructor (val countriesApi: CountriesApi){

    fun getAllCountries(): Single<List<Country>> {
        return countriesApi.getAllCountries()
    }
}