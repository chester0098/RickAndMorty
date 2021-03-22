package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.api.RickAndMortyAPI
import com.example.rickandmorty.model.db.RealmDatabase
import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.presenter.CharacterResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm

class CharactersRepository(private val api: RickAndMortyAPI, private val db: RealmDatabase) {
    private lateinit var realm: Realm
    fun getCharacters(pageNum: Int, characterResponse: CharacterResponse) {
        api.getCharacters(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    characterResponse.addCharacterToRecycler(
                        response.results,
                        response.info.pages
                    )
                    db.addCharactersToRealm(response.results)
                },
                { characterResponse.onError(db.getCharactersFromRealm()) })
    }

    fun getCharacterFromDB(id: Int): Result {
        return db.getOneCharacterFromRealm(id)
    }
}