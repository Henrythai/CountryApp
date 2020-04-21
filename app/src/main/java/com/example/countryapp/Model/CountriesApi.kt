package com.example.countryapp.Model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountriesApi {
    @GET("/DevTides/countries/master/countriesV2.json")
    fun getAllCountries():Single<List<Country>>
}