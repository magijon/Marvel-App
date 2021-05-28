package com.mebeal.marvelapp.data.provider.impl

import com.mebeal.marvelapp.data.network.BaseDataSource
import com.mebeal.marvelapp.data.network.CharacterService
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import javax.inject.Inject

class NetworkProviderImpl @Inject constructor(private val characterService: CharacterService) : NetworkProvider, BaseDataSource() {

    override suspend fun getAllCharacters(offset: Int): Resource<Any> = getResult {
        characterService.getAllCharacters("", offset, "" , "", "")
    }

    override suspend fun getOneCharacter(id: Int): Resource<Any> = getResult {
        characterService.getCharacter(id, "", "", "")
    }

}