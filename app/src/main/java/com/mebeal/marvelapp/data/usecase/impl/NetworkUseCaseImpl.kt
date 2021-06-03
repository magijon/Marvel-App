package com.mebeal.marvelapp.data.usecase.impl

import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import javax.inject.Inject

class NetworkUseCaseImpl @Inject constructor(private val networkProvider: NetworkProvider) :
    NetworkUseCase {
    override suspend fun getAllCharacters(offset: Int): Resource<CharacterListResponse> {
        return networkProvider.getAllCharacters(offset)
    }


    override suspend fun getOneCharacter(id: Int): Resource<CharacterCallResponse> {
        return networkProvider.getOneCharacter(id)
    }

}