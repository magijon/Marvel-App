package com.mebeal.marvelapp.data.provider.contract

import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterListResponse

interface NetworkProvider {
    suspend fun getAllCharacters(offset: Int): Resource<CharacterListResponse>
    suspend fun getOneCharacter(id: Int): Resource<CharacterCallResponse>
}