package com.example.countryapp

import android.app.Application
import android.content.Context
import com.example.countryapp.Di.CountryComponent
import com.example.countryapp.Di.DaggerCountryComponent

class App : Application() {

    companion object {
        lateinit var component: CountryComponent private set
        @Volatile
        private var instance: App? = null
        private val LOCK = Any()
        operator fun invoke() = instance ?: synchronized(LOCK) { instance ?: buildApp().also { instance = it } }

        private fun buildApp() = App()
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerCountryComponent.create()
    }


}