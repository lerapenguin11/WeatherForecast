package com.example.weatherforecast.business.repos

import android.annotation.SuppressLint
import android.util.Log
import com.example.weatherforecast.business.api.ApiProvider
import com.example.weatherforecast.business.model.WeatherDataModel
import com.example.weatherforecast.business.room.WeatherDataEntity
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

const val TAG = "MAIN_REPO"

class MainRepository(api : ApiProvider) : BaseRepository<MainRepository.ServerResponse>(api) {
    private val gson = Gson()
    private val dbAccess = db.getWeatherDao()
    @SuppressLint("CheckResult")
    fun reloadData(lat : String, lon : String){
        Observable.zip(
            api.providerWeatherApi().getWeatherForecast(lat, lon),
            api.provideGeoCodeApi().getCityByCoordinates(lat, lon).map{
                it.asSequence()
                    .map { model -> model.name}
                    .toList()
                        //TODO настроить локализацию
                    .filterNotNull()
                    .first()
            }
        ) { weatherData, geoCode -> ServerResponse(geoCode, weatherData) }

            .subscribeOn(Schedulers.io())
            .doOnNext{
                dbAccess.insertWeatherData(WeatherDataEntity(data = gson.toJson(it.weatherData), city = it.cityName)) //добавление в бд
            }
            .onErrorResumeNext {
                Observable.just(ServerResponse(
                    dbAccess.getWeatherData().city,
                    gson.fromJson(dbAccess.getWeatherData().data, WeatherDataModel :: class.java),
                    it
                ))
            }//здесь будет извлечение объекта из бд
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dataEmitter.onNext(it)
                },
                {
                    Log.d(TAG, "reloadData: $it")
                })
    }

    data class ServerResponse(val cityName : String,
                              val weatherData : WeatherDataModel,
                              val error : Throwable? = null)
}