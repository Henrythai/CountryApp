package com.example.countryapp.Model

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CountryService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val countriesApi: CountriesApi

    init {
        countriesApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    fun getAllCountries(): Single<List<Country>> {
        return countriesApi.getAllCountries()
    }
}