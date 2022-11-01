package com.example.weatherforecast.business.model

import com.example.weatherforecast.presenters.BasePresenter
import com.example.weatherforecast.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    //TODO переменная репо

    override fun enable() {

    }

    fun refresh(last : String, lon : String){
        viewState.setLoading(true)
        //TODO обращение к репо
    }
}