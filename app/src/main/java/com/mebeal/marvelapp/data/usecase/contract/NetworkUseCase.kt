package com.mebeal.marvelapp.data.usecase.contract

import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterListResponse

interface NetworkUseCase {
    suspend fun getAllCharacters(offset: Int): Resource<CharacterListResponse>
    suspend fun getOneCharacter(id: Int): Resource<CharacterCallResponse>
}