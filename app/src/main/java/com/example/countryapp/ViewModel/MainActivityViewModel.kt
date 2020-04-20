package com.example.countryapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.Model.Country

class MainActivityViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>();

    fun refresh(){

    }
}