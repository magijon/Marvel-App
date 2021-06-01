package com.mebeal.marvelapp.presentation.navigation

import androidx.fragment.app.Fragment
import com.mebeal.marvelapp.presentation.model.ScreenFlowState

interface NavigationContract {

    fun handle(fragment: Fragment, screenFlowState: ScreenFlowState)

}