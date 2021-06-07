package com.mebeal.marvelapp.presentation.fragment.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.presentation.model.ErrorDisplayModel
import com.mebeal.marvelapp.presentation.model.ScreenFlowState
import com.mebeal.marvelapp.presentation.utils.customview.SingleDialogDisplayModel
import com.mebeal.marvelapp.presentation.wrapper.RestrictedLiveData

abstract class BaseViewModel<T> : ViewModel() {

    private var _errorDisplay = MutableLiveData<ErrorDisplayModel>()
    val errorDisplay: LiveData<ErrorDisplayModel> = _errorDisplay

    protected var screenState = RestrictedLiveData<ScreenFlowState>()
    val screenFlowState: LiveData<ScreenFlowState> = screenState

    protected var _resourceData: MutableLiveData<Resource<T>> = MutableLiveData()
    var resourceData: LiveData<Resource<T>> = _resourceData

    open fun startLogic(additionalEntry: Any? = null) {}


    open fun onFailureGetData(message: String?) {
        screenState.setValue(ScreenFlowState.HideLoading)
        screenState.setValue(ScreenFlowState.ShowError(message ?: "An error has occurred", null))
    }

    open fun onLoadingGetData() {
        screenState.setValue(ScreenFlowState.ShowLoading)
    }

    open fun onSuccessGetData(data: T?) {
        screenState.setValue(ScreenFlowState.HideLoading)
    }

    open fun showMessageError(message: String, action: (() -> Unit)?) {
        screenState.setValue(
            ScreenFlowState.ShowSingleDialog(
                SingleDialogDisplayModel(
                    "Error",
                    message,
                    "Accept"
                ), action
            )
        )
    }
}