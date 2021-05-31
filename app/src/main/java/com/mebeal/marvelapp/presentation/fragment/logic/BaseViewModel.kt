package com.mebeal.marvelapp.presentation.fragment.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.presentation.model.ErrorDisplayModel
import com.mebeal.marvelapp.presentation.model.ScreenFlowState
import com.mebeal.marvelapp.presentation.wrapper.RestrictedLiveData

abstract class BaseViewModel<T> : ViewModel() {

    private var _errorDisplay = MutableLiveData<ErrorDisplayModel>()
    val errorDisplay: LiveData<ErrorDisplayModel> = _errorDisplay

    protected var screenState = RestrictedLiveData<ScreenFlowState>()
    val screenFlowState: LiveData<ScreenFlowState> = screenState

    protected var _resourceData : LiveData<Resource<T>> = MutableLiveData()
    lateinit var resourceData : LiveData<Resource<T>>

    open fun startLogic(additionalEntry: Any? = null) {}
}