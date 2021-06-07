package com.mebeal.marvelapp.presentation.utils

import androidx.lifecycle.*
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.Resource.Status.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import kotlin.coroutines.suspendCoroutine

fun <A> performGetOperation(
    mutableLiveData: MutableLiveData<Resource<A>>,
    networkCall: suspend () -> Resource<A>?
) {
    GlobalScope.launch(Dispatchers.IO) {
            mutableLiveData.postValue(Resource.loading())
            val responseStatus : Resource<A>? = networkCall.invoke()
            when (responseStatus?.status) {
                SUCCESS -> {
                    mutableLiveData.postValue(responseStatus)
                }
                else -> {
                    mutableLiveData.postValue(Resource.error("Don't get data"))
                }
            }
    }
}

