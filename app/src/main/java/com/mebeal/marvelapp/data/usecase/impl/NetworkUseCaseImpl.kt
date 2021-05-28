package com.mebeal.marvelapp.data.usecase.impl

import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import javax.inject.Inject

class NetworkUseCaseImpl @Inject constructor(private val networkProvider: NetworkProvider) : NetworkUseCase {
    override suspend fun getAllCharacters(offset: Int) =
        networkProvider.getAllCharacters(offset)


    override suspend fun getOneCharacter(id: Int) =
        networkProvider.getOneCharacter(id)

}