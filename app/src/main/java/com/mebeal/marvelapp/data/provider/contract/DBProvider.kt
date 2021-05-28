package com.mebeal.marvelapp.data.provider.contract

interface DBProvider {
    fun getAllCharacters()
    fun getOneCharacter(id : Int)
    fun insertAllCharacters(list : List<Any>)
    fun insertOneCharacter(character : Any)
}