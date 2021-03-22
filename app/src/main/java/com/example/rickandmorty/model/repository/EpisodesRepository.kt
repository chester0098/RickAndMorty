package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.api.RickAndMortyAPI
import com.example.rickandmorty.model.db.RealmDatabase
import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.presenter.EpisodesAndCharacterResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class EpisodesRepository(private val api: RickAndMortyAPI, private val db: RealmDatabase) {
    fun getCharacterAndEpisode(
        id: Int,
        episodesAndCharacterResponse: EpisodesAndCharacterResponse
    ) {
        val result: Result = db.getOneCharacterFromRealm(id)
        episodesAndCharacterResponse.addCharacterInfo(result)

        val episodeUrlList = result.episode

        Observable.range(0, episodeUrlList!!.size)
            .flatMap { i -> api.getEpisode(episodeUrlList[i]!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    episodesAndCharacterResponse.addEpisodeToRecycler(response)
                    db.addEpisodesToRealm(response)
                },
                { episodesAndCharacterResponse.onError(db.getEpisodesFromRealm(episodeUrlList)) })
    }
}