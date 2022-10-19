package com.example.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherforecast.databinding.ActivityMainBinding

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.mainCityNameTv.text = "Moscow"
        binding.mainDateTv.text = "14 октября"
        binding.mainWeatherConditionDescription.text = "Clear sky"
        binding.mainTemp.text = "25\u00B0"
        binding.mainTempMinTv.text = "18"
        binding.mainTempMaxTv.text = "29"
        binding.mainPressureMuTv.text = "1,4 hPa"
        binding.mainHumidityMuTv.text = "87 %"
        binding.mainWindSpeedMuTv.text = "5 m/s"
        binding.mainSunriseMuTv.text = "6:20"
        binding.mainSunsetMuTv.text = "22:50"
        binding.mainWeatherConditionIcon.setImageResource(R.drawable.ic_sun)
        binding.mainWeatherImage.setImageResource(R.mipmap.clowd3x)*/
    }
}