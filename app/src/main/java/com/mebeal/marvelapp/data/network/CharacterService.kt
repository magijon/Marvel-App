package com.mebeal.marvelapp.data.network

import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("v1/public/characters")
    suspend fun getAllCharacters(
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharacterListResponse>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharacterCallResponse>
}