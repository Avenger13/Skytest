package com.test.skytest.presentation

import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter


open class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    protected val disposables = CompositeDisposable()

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }


    protected fun Disposable.autoDispose() = disposables.add(this)

    protected fun <T> Observable<T>.ioToMain(): Observable<T> = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    protected fun <T> Single<T>.ioToMain(): Single<T> = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    protected fun <T> Single<T>.wrapByNotification() =
        map { next: T ->
            if (next == null) Notification.createOnComplete<T>()
            else Notification.createOnNext(next)
        }.onErrorReturn { error -> Notification.createOnError(error) }
}