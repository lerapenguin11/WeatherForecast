package com.example.weatherforecast.business.api

import com.example.weatherforecast.business.model.GeoCodeModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {

    @GET("geo/1.0/direct?")
    fun getCityByCoordinates(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("limit") limit : String = "10",
        @Query("appid") id : String = "7e987a61c1cd7c9c05a7c7f95cb703ee"
    ) : Observable<List<GeoCodeModel>>
}