package com.mebeal.marvelapp.presentation.utils.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.presentation.model.ScreenFlowState
import com.mebeal.marvelapp.presentation.model.ScreenFlowState.NavigateBackFragment
import com.mebeal.marvelapp.presentation.model.ScreenFlowState.NavigateToCharacterDetail
import com.mebeal.marvelapp.presentation.navigation.NavigationContract
import com.mebeal.marvelapp.presentation.utils.SELECT_ID

object NavigationHandler : NavigationContract {

    override fun handle(fragment: Fragment, screenFlowState: ScreenFlowState) {
        when (screenFlowState) {

            is NavigateToCharacterDetail -> findNavController(fragment).apply {
                navigate(
                    R.id.characterDetailFragment,
                    createBundle { putInt(SELECT_ID, screenFlowState.id) },
                    createNavOptions()
                )
            }

            is NavigateBackFragment -> repeat(screenFlowState.times) { findNavController(fragment).popBackStack() }

            else -> {
            }
        }
    }

    private fun createBundle(bundleSaving: Bundle.() -> Unit) = Bundle().apply { bundleSaving() }

    private fun createNavOptions(
        popUpDestination: Int? = null,
        clearStack: Boolean = false,
        isInclusive: Boolean = false,
        inverse: Boolean = false
    ) =
        NavOptions.Builder().apply {
            setEnterAnim(if (!inverse) R.anim.slide_in_right else R.anim.slide_in_left)
            setExitAnim(if (!inverse) R.anim.slide_out_left else R.anim.slide_out_right)
            setPopEnterAnim(if (!inverse) R.anim.slide_in_left else R.anim.slide_in_right)
            setPopExitAnim(if (!inverse) R.anim.slide_out_right else R.anim.slide_out_left)
            setLaunchSingleTop(clearStack)
            popUpDestination?.let { setPopUpTo(it, isInclusive) }
        }.build()


}