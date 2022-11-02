package com.example.weatherforecast

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.business.model.DailyWeatherModel
import com.example.weatherforecast.business.model.HourlyWeatherModel
import com.example.weatherforecast.business.model.MainPresenter
import com.example.weatherforecast.business.model.WeatherData
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.view.MainView
import com.example.weatherforecast.view.adapter.MainDailyListAdapter
import com.example.weatherforecast.view.adapter.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

private lateinit var binding : ActivityMainBinding

const val TAG = "GEO_TEST"

class MainActivity : MvpAppCompatActivity(), MainView {

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var mLocation : Location

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        binding.mainHourlyList.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.mainDailyList.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        mainPresenter.enable()

        geoService.requestLocationUpdates(locationRequest, geoCallback, null)
    }

    private fun initViews(){
        binding.mainCityNameTv.text = "Moscow"
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
        binding.mainWeatherImage.setImageResource(R.mipmap.clowd3x)
    }

    //-----------location code-----------

    private fun initLocationRequest() : com.google.android.gms.location.LocationRequest{
        val request = LocationRequest.create()
        return request.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private val geoCallback = object : LocationCallback(){
        override fun onLocationResult(geo: LocationResult) {
            Log.d(TAG, "geoCallback: ${geo.locations.size}")
            for(location in geo.locations){
                mLocation = location
                //вызов презентера
                mainPresenter.refresh(mLocation.latitude.toString(), mLocation.longitude.toString())
                Log.d(TAG, "onLocationResult: lat: ${location.latitude} lon: ${location.longitude}")
            }
        }
    }

    //-----------location code-----------

    //-----------moxy code---------------

    override fun displayLocation(data: String) {
        binding.mainCityNameTv.text = data
    }

    override fun displayCurrentData(data: WeatherData) {
        binding.mainCityNameTv.text = "Moscow"
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
        binding.mainWeatherImage.setImageResource(R.mipmap.clowd3x)
    }

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (binding.mainHourlyList.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (binding.mainDailyList.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }

    //-----------moxy code---------------
}

