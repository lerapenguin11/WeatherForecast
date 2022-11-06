package com.example.weatherforecast.business.repos

import com.example.weatherforecast.business.api.ApiProvider
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepository<T>(val api : ApiProvider) {

    val dataEmitter : BehaviorSubject<T> = BehaviorSubject.create()
}