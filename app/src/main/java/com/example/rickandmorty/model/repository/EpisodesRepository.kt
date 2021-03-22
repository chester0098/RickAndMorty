package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.api.RickAndMortyAPI
import com.example.rickandmorty.model.db.RealmDatabase
import com.example.rickandmorty.presenter.EpisodesResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class EpisodesRepository(private val api: RickAndMortyAPI, private val db: RealmDatabase) {
    fun getCharacterAndEpisode(
        episodeUrlList: List<String>,
        episodesResponse: EpisodesResponse
    ) {
        Observable.range(0, episodeUrlList.size)
            .flatMap { i -> api.getEpisode(episodeUrlList[i]) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    episodesResponse.addEpisodeToRecycler(response)
                    db.addEpisodesToRealm(response)
                },
                { episodesResponse.onError(db.getEpisodesFromRealm(episodeUrlList)) })
    }
}