package com.example.countryapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countryapp.Model.CountriesApi
import com.example.countryapp.Model.Country
import com.example.countryapp.Model.CountryService
import com.example.countryapp.ViewModel.MainActivityViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class MainActivityViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()


//    @InjectMocks
//    lateinit var countriesApi:CountriesApi

    private val BASE_URL = "https://raw.githubusercontent.com/"

    @Mock
    val countriesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    @Mock
    val countryService = CountryService(countriesApi)

    @InjectMocks
    var mainActivityViewModel = MainActivityViewModel(countryService)

    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testRefresh_getCountrySuccess() {
        val country = Country("country", "capital", "plag")
        val countryList = arrayListOf(country)

        testSingle = Single.just(countryList)

        Mockito.`when`(countryService.getAllCountries()).thenReturn(testSingle)

        mainActivityViewModel.refresh()

        Assert.assertEquals(250, mainActivityViewModel.countries.value?.size)
        mainActivityViewModel.loading.value?.let { Assert.assertFalse(it) }
        mainActivityViewModel.loadError.value?.let { Assert.assertFalse(it) }
    }

    @Test
    fun testRefresh_getCountryFail() {
        testSingle = Single.error(Throwable())
        Mockito.`when`(countryService.getAllCountries()).thenReturn(testSingle)

        mainActivityViewModel.refresh()

        mainActivityViewModel.loadError.value?.let { Assert.assertTrue(it) }
        mainActivityViewModel.loading.value?.let { Assert.assertFalse(it) }
    }


    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(
                run: Runnable,
                delay: Long, unit: TimeUnit
            ): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false, false)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
    }


}