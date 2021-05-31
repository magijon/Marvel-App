package com.mebeal.marvelapp.presentation.utils

import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel

fun getURLImageList(character: CharactersDisplayModel): String = getURLImage(character, SIZE_IMAGE_LIST)
fun getURLImageDetail(character: CharactersDisplayModel): String =
    getURLImage(character, SIZE_IMAGE_CHARACTER_DETAIL)

fun getURLImage(character: CharactersDisplayModel, size: String): String =
    "${character.path}/$size.${character.extension}"