package com.mebeal.marvelapp.data.provider.impl

import com.mebeal.marvelapp.data.network.BaseDataSource
import com.mebeal.marvelapp.data.network.CharacterService
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.model.mappers.CharactersDisplayModelMapper
import javax.inject.Inject

class NetworkProviderImpl @Inject constructor(private val characterService: CharacterService) : NetworkProvider, BaseDataSource() {

    override suspend fun getAllCharacters(offset: Int): Resource<CharacterListResponse> = getResult{
        characterService.getAllCharacters("", offset, "" , "", "")
    }

    override suspend fun getOneCharacter(id: Int): Resource<CharacterCallResponse> = getResult {
        characterService.getCharacter(id, "", "", "")
    }

}