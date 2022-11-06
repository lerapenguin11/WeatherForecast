package com.example.weatherforecast.presenters

import com.example.weatherforecast.business.api.ApiProvider
import com.example.weatherforecast.business.repos.MainRepository
import com.example.weatherforecast.presenters.BasePresenter
import com.example.weatherforecast.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    //TODO переменная репо
    private val repo = MainRepository(ApiProvider())

    override fun enable() {
        repo.dataEmitter.subscribe {response ->
            viewState.displayCurrentData()
            viewState.displayDailyData()
            viewState.displayLocation()
            viewState.displayHourlyData()
            response.error?.let{viewState.displayError()}

        }
    }

    fun refresh(last : String, lon : String){
        viewState.setLoading(true)
        //TODO обращение к репо
    }
}