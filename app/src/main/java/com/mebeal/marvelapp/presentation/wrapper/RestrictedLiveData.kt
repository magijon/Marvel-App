package com.mebeal.marvelapp.presentation.wrapper

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class RestrictedLiveData<T> : MutableLiveData<T>() {

    private val isHandled = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        isHandled.set(false)
        super.observe(owner, Observer<T> { if (isHandled.get()) observer.onChanged(it) })
    }

    @MainThread
    override fun setValue(value: T) {
        isHandled.set(true)
        super.setValue(value)
    }

    @WorkerThread
    override fun postValue(value: T) {
        isHandled.set(true)
        super.postValue(value)
    }

}