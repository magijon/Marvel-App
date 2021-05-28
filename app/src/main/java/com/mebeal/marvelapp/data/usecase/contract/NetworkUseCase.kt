package com.mebeal.marvelapp.data.usecase.contract

import com.mebeal.marvelapp.data.network.Resource

interface NetworkUseCase {
    suspend fun getAllCharacters(offset : Int) : Resource<Any>
    suspend fun getOneCharacter(id : Int) : Resource<Any>
}