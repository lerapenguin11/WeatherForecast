package com.example.weatherforecast.business.room

import androidx.room.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherData id = 1")
    fun getWeatherData() : WeatherDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE) //чтобы невозникало конфликта из-за id
    fun insertWeatherData(data : WeatherDataEntity)

    @Update
    fun updateWeatherData(data: WeatherDataEntity)

    @Delete
    fun deleteWeatherData(data: WeatherDataEntity)
}