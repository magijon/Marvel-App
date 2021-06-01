package com.mebeal.marvelapp.di.modules

import com.mebeal.marvelapp.presentation.navigation.NavigationContract
import com.mebeal.marvelapp.presentation.utils.navigation.NavigationHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun providesNavigationContract(): NavigationContract = NavigationHandler

}