package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result

interface CharacterResponse {
    fun addCharacterToRecycler(listCharacters: List<Result>)
    fun onError()
}