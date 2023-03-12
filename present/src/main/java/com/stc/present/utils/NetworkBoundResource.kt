package com.stc.present.utils

import android.annotation.SuppressLint
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<L, R> @SuppressLint("CheckResult") internal constructor(emitter: FlowableEmitter<L>) {
    abstract val remote: Single<R>
    abstract val local: Flowable<L>

    abstract fun saveCallResult(data: L)
    abstract fun mapper(): Function<R, L>?

    init {
        val firstDataDisposable = local
            .subscribe { value: L -> emitter.onNext(value) }

        remote.map(mapper())
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({ localDataType: L ->
                firstDataDisposable.dispose()
                saveCallResult(localDataType)
                local.subscribe { value: L -> emitter.onNext(value) }
            }) { error: Throwable? -> local.subscribe({ value: L -> emitter.onNext(value) }) { error: Throwable? -> emitter.onError(error!!) } }
    }
}