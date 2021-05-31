package com.mebeal.marvelapp.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mebeal.marvelapp.presentation.activity.logic.MainViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.CharacterDetailViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.CharactersViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.factory.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Module
abstract class ViewModelBinding {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(charactersViewModel: CharactersViewModel?): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModel(characterDetailViewModel: CharacterDetailViewModel?): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel?): ViewModel


    //  Factory
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?

    //  Annotation Key
    @Documented
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}