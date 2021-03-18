package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.retrofit.RetrofitClient
import com.example.rickandmorty.presenter.CharacterResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CharactersRepository {
    fun getCharacters(pageNum: Int, characterResponse: CharacterResponse) {
        RetrofitClient.rickAndMortyAPI.getCharacters(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> characterResponse.addCharacterToRecycler(response.results) },
                { characterResponse.onError() })
    }
}