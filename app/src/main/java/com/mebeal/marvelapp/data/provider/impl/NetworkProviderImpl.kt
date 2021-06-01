package com.mebeal.marvelapp.data.provider.impl

import com.mebeal.marvelapp.BuildConfig
import com.mebeal.marvelapp.data.network.BaseDataSource
import com.mebeal.marvelapp.data.network.CharacterService
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import com.mebeal.marvelapp.presentation.utils.MD5.Companion.getHashComplete
import com.mebeal.marvelapp.presentation.utils.MD5.Companion.timestamp
import javax.inject.Inject

class NetworkProviderImpl @Inject constructor(private val characterService: CharacterService) :
    NetworkProvider, BaseDataSource() {

    override suspend fun getAllCharacters(offset: Int): Resource<CharacterListResponse> =
        getResult {
            val hash = getHashComplete(BuildConfig.API_KEY, BuildConfig.PRIVATE_KEY)
            characterService.getAllCharacters(
                "name",
                offset,
                timestamp,
                BuildConfig.API_KEY,
                hash
            )
        }

    override suspend fun getOneCharacter(id: Int): Resource<CharacterCallResponse> = getResult {
        val hash = getHashComplete(BuildConfig.API_KEY, BuildConfig.PRIVATE_KEY)
        characterService.getCharacter(id, timestamp, BuildConfig.API_KEY, hash)
    }


}