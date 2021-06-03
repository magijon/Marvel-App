package com.mebeal.marvelapp.presentation.activity.view

import android.view.View
import android.view.animation.AlphaAnimation
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.databinding.ActivityMainBinding
import com.mebeal.marvelapp.presentation.activity.logic.MainViewModel
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

    override fun setBindingVariables() {}

    override fun getLoader(): View = dataBinding.progressBar

    override fun initViews() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(dataBinding.toolbar)
        dataBinding.toolbar.setupWithNavController(navController, appBarConfiguration)

        animPresentation()
    }

    private fun animPresentation() {
        dataBinding.presentationLayout.animate().alpha(0F).setDuration(2000);
    }
}