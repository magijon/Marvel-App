package com.mebeal.marvelapp.di.modules.viewmodel

import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.presentation.activity.logic.MainViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.CharacterDetailViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.CharactersViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelBinding::class])
class ViewModelModule {
    @Provides
    fun providesCharactersViewModel(networkUseCase: NetworkUseCase) = CharactersViewModel(networkUseCase)

    @Provides
    fun providesCharacterDetailViewModel() = CharacterDetailViewModel()

    @Provides
    fun providesMainViewModel() = MainViewModel()
}