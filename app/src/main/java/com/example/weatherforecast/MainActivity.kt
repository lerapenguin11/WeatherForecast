package com.example.weatherforecast

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.view.adapter.MainDailyListAdapter
import com.example.weatherforecast.view.adapter.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private lateinit var binding : ActivityMainBinding

const val GEO_LOCATION_REQUEST_COD_SUCCESS = 1
const val TAG = "GEO_TEST"

class MainActivity : AppCompatActivity() {

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var mLocation : Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()

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
                Log.d(TAG, "onLocationResult: lat: ${location.latitude} lon: ${location.longitude}")
            }
        }
    }

    //-----------location code-----------

    //-----------initial activity--------
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: $requestCode")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //зауск MainActivity
    }

    private fun checkPermission(){
        if((ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)) != PackageManager.PERMISSION_GRANTED)){
            MaterialAlertDialogBuilder(this)
                .setTitle("Приложению нужны данные по геолокации")
                .setMessage("Пожалуйста, разрешите доступ к вашим гео данным для продолжения работы приложения")
                .setPositiveButton("Ок"){_,_ ->
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS)
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS)
                }
                .setNegativeButton("Закрыть"){ dialog, _ -> dialog.dismiss()}
                .create()
                .show()
            }
        }
    }

    //-----------initial activity--------