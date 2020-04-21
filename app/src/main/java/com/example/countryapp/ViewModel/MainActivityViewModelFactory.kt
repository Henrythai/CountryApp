package com.example.countryapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.Model.CountryService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainActivityViewModelFactory @Inject constructor(val countryService: CountryService) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(countryService) as T
    }

}