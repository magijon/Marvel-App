package com.mebeal.marvelapp.data.network.models

data class CharacterListResponse(
    val data: CharacterList
)

data class CharacterList(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterResponse>
)