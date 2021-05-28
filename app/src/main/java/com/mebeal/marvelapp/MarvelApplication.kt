package com.mebeal.marvelapp

import androidx.multidex.MultiDexApplication
import com.mebeal.marvelapp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MarvelApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjector()
    }

    private fun initializeDependencyInjector() {
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }
}