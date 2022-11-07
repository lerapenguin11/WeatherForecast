package com.example.weatherforecast.business.repos

import android.util.Log
import com.example.weatherforecast.business.api.ApiProvider
import com.example.weatherforecast.business.model.WeatherDataModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

const val TAG = "MAIN_REPO"

class MainRepository(api : ApiProvider) : BaseRepository<MainRepository.ServerResponse>(api) {

    fun reloadData(lat : String, lon : String){
        Observable.zip(
            api.providerWeatherApi().getWeatherForecast(lat, lon),
            api.provideGeoCodeApi().getCityByCoordinates(lat, lon).map{
                it.asSequence()
                    .map { model -> model.name}
                    .toList()
                    .filterNotNull()
                    .first()
            }
        ) { weatherData, geoCode -> ServerResponse(geoCode, weatherData) }

            .subscribeOn(Schedulers.io())
            .doOnNext{/* TODO тут будет добавление объекта в бд */}
            /*.onErrorResumeNext { TODO здесь будет извлечение объекта из бд }*/
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