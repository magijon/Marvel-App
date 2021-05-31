package com.mebeal.marvelapp.presentation.model.mappers

import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.network.models.CharacterResponse
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel

class CharactersDisplayModelMapper {

    companion object {
        fun fromCharacterListResponse(list: CharacterListResponse): List<CharactersDisplayModel> =
            mutableListOf<CharactersDisplayModel>().apply {
                list.data.results.map {
                    this.add(transformCharacterResponse(it))
                }
            }

        fun fromCharacterResponse(characterResponse: CharacterResponse): CharactersDisplayModel =
            transformCharacterResponse(characterResponse)


        private fun transformCharacterResponse(characterResponse: CharacterResponse): CharactersDisplayModel =
            with(characterResponse) {
                CharactersDisplayModel(
                    id,
                    description,
                    name,
                    thumbnail?.path,
                    thumbnail?.extension,
                    modified
                )
            }
    }
}