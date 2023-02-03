package com.example.weatherforecast.business.repos

import com.example.weatherforecast.App
import com.example.weatherforecast.business.api.ApiProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepository<T>(val api : ApiProvider) {

    val dataEmitter : BehaviorSubject<T> = BehaviorSubject.create()
    protected val db by lazy { App.db }

    //rxjava 2 поток для бд
    protected fun roomTransaction(
        transaction: () -> T
    ) = Observable.fromCallable { transaction() }
        .subscribeOn(Schedulers.io()) //раздваивание потоков
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{dataEmitter.onNext(it)}
}