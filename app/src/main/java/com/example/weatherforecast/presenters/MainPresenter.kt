package com.example.weatherforecast.presenters

import android.util.Log
import com.example.weatherforecast.business.api.ApiProvider
import com.example.weatherforecast.business.repos.MainRepository
import com.example.weatherforecast.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    private val repo = MainRepository(ApiProvider())

    override fun enable() {
        repo.dataEmitter.subscribe {response ->
            Log.d("MAIN_REPO", "Presenter enable(): $response" )
            viewState.displayCurrentData(response.weatherData)
            viewState.displayDailyData(response.weatherData.daily)
            viewState.displayLocation(response.cityName)
            viewState.displayHourlyData(response.weatherData.hourly)
            response.error?.let{viewState.displayError(response.error)}
        }
    }

    fun refresh(lat : String, lon : String){
        viewState.setLoading(true)
        repo.reloadData(lat, lon)
    }
}