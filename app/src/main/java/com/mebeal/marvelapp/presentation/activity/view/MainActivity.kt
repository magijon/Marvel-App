package com.mebeal.marvelapp.presentation.activity.view

import android.view.View
import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.databinding.ActivityMainBinding
import com.mebeal.marvelapp.presentation.activity.logic.MainViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.BaseViewModel
import com.mebeal.marvelapp.presentation.utils.gone
import com.mebeal.marvelapp.presentation.utils.isVisible
import com.mebeal.marvelapp.presentation.utils.visible

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun showToolbar() {
        dataBinding.appBarlayout.let { toolbar -> if (toolbar.isVisible().not()) toolbar.visible() }
    }

    override fun hideToolbar() {
        dataBinding.appBarlayout.let { toolbar -> if (toolbar.isVisible()) toolbar.gone() }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setBindingVariables() {

    }

    override fun getLoader(): View = dataBinding.progressBar
}