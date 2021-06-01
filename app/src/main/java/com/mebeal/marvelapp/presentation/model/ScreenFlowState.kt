package com.mebeal.marvelapp.presentation.model

import com.mebeal.marvelapp.presentation.utils.Action
import com.mebeal.marvelapp.presentation.utils.DualResponseClickAction
import com.mebeal.marvelapp.presentation.utils.customview.SingleDialogDisplayModel

sealed class ScreenFlowState {

    class ShowSingleDialog(
        val singleDialogDisplayModel: SingleDialogDisplayModel,
        val onContinueAction: Action?
    ) : ScreenFlowState()

    class ShowDualDialog(
        val title: String,
        val content: String,
        val acceptButtonTitle: String,
        val rejectButtonTitle: String,
        val onResponseAction: DualResponseClickAction
    ) : ScreenFlowState()

    class NavigateToCharacterDetail(val id: Int) : ScreenFlowState()

    object ShowLoading : ScreenFlowState()

    object HideLoading : ScreenFlowState()

    object RemoveDialog : ScreenFlowState()

    object ShowError : ScreenFlowState()

    class NavigateBackFragment(val times: Int = 1) : ScreenFlowState()

}