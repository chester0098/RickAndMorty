package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result

interface CharacterResponse {
    fun addCharacterToRecycler(listCharacters: List<Result>, maxPageNum: Int)
    fun onError(listCharacters: List<Result>)
}