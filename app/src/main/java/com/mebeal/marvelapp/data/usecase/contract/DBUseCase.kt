package com.mebeal.marvelapp.data.usecase.contract

interface DBUseCase {
    fun getAllCharacters()
    fun getOneCharacter(id : Int)
    fun insertAllCharacters(list : List<Any>)
    fun insertOneCharacter(character : Any)
}