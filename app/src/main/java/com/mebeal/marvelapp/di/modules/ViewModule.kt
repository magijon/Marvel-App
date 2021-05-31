package com.mebeal.marvelapp.di.modules

import com.mebeal.marvelapp.presentation.activity.view.MainActivity
import com.mebeal.marvelapp.presentation.fragment.view.CharacterDetailFragment
import com.mebeal.marvelapp.presentation.fragment.view.CharactersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun bindCharactersFragment() : CharactersFragment

    @ContributesAndroidInjector
    abstract fun bindCharacterDetailFragment() : CharacterDetailFragment
}