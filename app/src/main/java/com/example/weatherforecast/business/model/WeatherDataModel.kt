package com.example.weatherforecast.business.model

import com.mikhailcor.simpleweatherapp.business.model.Current

data class WeatherDataModel(
    val current: Current,
    val daily: List<DailyWeatherModel>,
    val hourly: List<HourlyWeatherModel>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)