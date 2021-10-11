package com.test.skytest.presentation

import com.test.skytest.App
import com.test.skytest.domain.Result
import com.test.skytest.presentation.error.MvpErrorPresenter
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter


open class BasePresenter<V : BaseView> : MvpPresenter<V>() {
    protected val disposables = CompositeDisposable()
    protected val resource: Resource<Int> = App.appComponent.resource
    protected val errorPresenter: MvpErrorPresenter<V> = MvpErrorPresenter(viewState, resource)

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }


    protected fun Disposable.autoDispose() = disposables.add(this)

    protected fun <T> Observable<T>.ioToMain(): Observable<T> = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    protected fun <T> Observable<T>.doOnAny(action: () -> Unit): Observable<T> =
        doOnEach { action() }.doOnError { action() }

    protected fun <T> Observable<T>.observeOnMain(): Observable<T> =
        observeOn(AndroidSchedulers.mainThread())

    protected fun <T> Single<T>.ioToMain(): Single<T> = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    protected fun <T> Single<T>.wrapByNotification() =
        map { next: T ->
            if (next == null) Notification.createOnComplete<T>()
            else Notification.createOnNext(next)
        }.onErrorReturn { error -> Notification.createOnError(error) }

    /**
     * <p>Subscribe to successful result of {@link io.reactivex.Single Single}
     *
     */
    protected fun <T> Single<Result<T>>.onSuccess(onSuccess: (T) -> Unit): Disposable {
        return subscribe(Consumer {
            when (it) {
                is Result.Success -> onSuccess.invoke(it.data)
                is Result.Error -> errorPresenter.resolve(it.error)
            }
        })
    }

}