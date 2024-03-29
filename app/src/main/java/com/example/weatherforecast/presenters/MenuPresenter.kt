package com.example.weatherforecast.presenters

import com.example.weatherforecast.business.api.ApiProvider
import com.example.weatherforecast.business.model.GeoCodeModel
import com.example.weatherforecast.business.repos.MenuRepository
import com.example.weatherforecast.business.repos.SAVED
import android.util.Log
import com.mikhailcor.simpleweatherapp.view.MenuView

class MenuPresenter : BasePresenter<MenuView>() {
    private val repo = MenuRepository(ApiProvider())

    override fun enable() {
        repo.dataEmitter.subscribe{
            viewState.setLoading(false)
            if(it.purpose == SAVED){
                Log.d("123321", "enable: SAVED ${it.data}")
                viewState.fillFavoriteList(it.data)
            }else{
                Log.d("123321", "enable: CURRENT ${it.data}")

                viewState.fillPredictionList(it.data)
            }
        }
    }

    fun searchFor(str: String){
        repo.getCities(str)
    }

    fun removeLocation(data: GeoCodeModel){
        repo.remove(data)
    }

    fun saveLocation(data: GeoCodeModel){
        repo.add(data)
    }

    fun getFavoriteList() {
        repo.updateFavorite()
    }
}