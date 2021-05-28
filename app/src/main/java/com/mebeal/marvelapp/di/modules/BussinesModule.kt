package com.mebeal.marvelapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mebeal.marvelapp.data.network.CharacterService
import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import com.mebeal.marvelapp.data.provider.impl.NetworkProviderImpl
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.data.usecase.impl.NetworkUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BussinesModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providerNetworkUseCase(networkProvider: NetworkProvider) : NetworkUseCase = NetworkUseCaseImpl(networkProvider)

    @Provides
    @Singleton
    fun providerNetWorkProvider(characterService: CharacterService) : NetworkProvider = NetworkProviderImpl(characterService)

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)
}