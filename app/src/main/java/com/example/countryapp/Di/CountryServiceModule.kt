package com.example.countryapp.Di

import com.example.countryapp.Model.CountryService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CountryServiceModule {

    @Singleton
    @Provides
    fun provideCountryService(): CountryService {
        return CountryService()
    }
}