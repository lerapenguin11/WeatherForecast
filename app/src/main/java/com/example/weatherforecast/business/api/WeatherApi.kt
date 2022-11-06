package com.example.weatherforecast.business.api

import com.example.weatherforecast.business.model.WeatherDataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/3.0/onecall?")
    fun getWeatherForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("exclude") exclude : String = "minutely, alerts",
        @Query("appid") appid : String = "7e987a61c1cd7c9c05a7c7f95cb703ee",
        @Query("lang") lang : String = "en"
    ) : Observable<WeatherDataModel>
}