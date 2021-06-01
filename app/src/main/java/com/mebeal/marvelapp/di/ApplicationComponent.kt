package com.mebeal.marvelapp.di

import com.mebeal.marvelapp.MarvelApplication
import com.mebeal.marvelapp.di.modules.BussinesModule
import com.mebeal.marvelapp.di.modules.UtilsModule
import com.mebeal.marvelapp.di.modules.ViewModule
import com.mebeal.marvelapp.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModule::class, ViewModelModule::class, BussinesModule::class, UtilsModule::class, AndroidInjectionModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: MarvelApplication) : Builder

        fun build() : ApplicationComponent
    }

    fun inject(marvelApplication: MarvelApplication)
}