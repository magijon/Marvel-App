package com.mebeal.marvelapp.data.network.models


data class Character(
    val id: Int,
    val description: String,
    val name: String,
    val path: String,
    val extension: String,
    val date: String
)

data class CharacterCallResponse(
    val data: CharacterDataResponse
)

data class CharacterDataResponse(
    val results: List<CharacterResponse>
)

class CharacterResponse(
    val id: Int,
    val description: String,
    val name: String,
    val thumbnail: ImageCharacter?,
    val modified: String
)

