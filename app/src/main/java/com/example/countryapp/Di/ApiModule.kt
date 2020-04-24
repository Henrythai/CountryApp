package com.example.countryapp.Di

import com.example.countryapp.Model.CountriesApi
import com.example.countryapp.Model.CountryService
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com/"

    @Singleton
    @Provides
    fun provideCountryApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
}