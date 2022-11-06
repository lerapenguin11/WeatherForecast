package com.example.weatherforecast.business.repos

import com.example.weatherforecast.business.api.ApiProvider

class MainRepository(api : ApiProvider) : BaseRepository<MainRepository.ServerResponse>(api) {

    fun reloadData(lat : String, lon : String){

    }
}