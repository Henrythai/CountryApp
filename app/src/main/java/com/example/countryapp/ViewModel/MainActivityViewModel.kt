package com.example.countryapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.Model.Country
import com.example.countryapp.Model.CountryService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivityViewModel(val countryService: CountryService) : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>();
    private val disposable = CompositeDisposable()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true

        disposable.add(
            countryService.getAllCountries()
                .toFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let { countryList ->
                        countries.value = countryList
                        loading.value = false
                    }
                },
                    { t ->
                        loading.value = false
                        loadError.value = true
                    })
        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

