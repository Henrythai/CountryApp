package com.example.countryapp.Di

import com.example.countryapp.Model.CountryService
import com.example.countryapp.View.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface CountryComponent {

    fun inject(countryService: CountryService)
    fun inject(mainActivity: MainActivity)
}