package com.example.weatherforecast.view

import com.example.weatherforecast.business.model.DailyWeatherModel
import com.example.weatherforecast.business.model.HourlyWeatherModel
import com.example.weatherforecast.business.model.WeatherDataModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView{

    @AddToEndSingle
    fun displayLocation(data : String)

    @AddToEndSingle
    fun displayCurrentData(data: WeatherDataModel)

    @AddToEndSingle
    fun displayHourlyData(data : List<HourlyWeatherModel>)

    @AddToEndSingle
    fun displayDailyData(data : List<DailyWeatherModel>)

    @AddToEndSingle
    fun displayError(error : Throwable)

    @AddToEndSingle
    fun setLoading(flag : Boolean)
}