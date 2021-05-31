package com.mebeal.marvelapp.presentation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <A> performGetOperation(
    networkCall: suspend () -> Resource<A>
): LiveData<Resource<A>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading<A>())
        val responseStatus = networkCall.invoke()
        when (responseStatus.status) {
            SUCCESS -> {
                emitSource(MutableLiveData(responseStatus))
            }
            else -> {
                emit(Resource.error<A>(responseStatus.message ?: "Error getting data"))
            }
        }
    }
