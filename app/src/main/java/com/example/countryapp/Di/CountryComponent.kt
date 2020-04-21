package com.example.countryapp.Di

import com.example.countryapp.View.MainActivity
import dagger.Component

@Component(modules = [CountryServiceModule::class])
interface CountryComponent {

    fun inject(mainActivity: MainActivity)
}