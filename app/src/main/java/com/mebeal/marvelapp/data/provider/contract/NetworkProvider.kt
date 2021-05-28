package com.mebeal.marvelapp.data.provider.contract

import com.mebeal.marvelapp.data.network.Resource

interface NetworkProvider {
    suspend fun getAllCharacters(offset : Int) : Resource<Any>
    suspend fun getOneCharacter(id : Int) : Resource<Any>
}