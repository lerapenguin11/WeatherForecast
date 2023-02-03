package com.example.weatherforecast.business.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherData")
data class WeatherDataEntity(
    @PrimaryKey
    val id : Int = 1,

    @ColumnInfo(name = "city") val city : String,

    @ColumnInfo(name = "json_data") val data : String
)