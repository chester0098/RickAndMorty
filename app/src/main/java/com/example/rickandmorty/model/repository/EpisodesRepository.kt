package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.retrofit.RetrofitClient
import com.example.rickandmorty.presenter.EpisodesResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class EpisodesRepository {
    fun getEpisodes(episodeUrlList: List<String>, episodesResponse: EpisodesResponse) {
        Observable.range(0, episodeUrlList.size)
            .flatMap { i -> RetrofitClient.rickAndMortyAPI.getEpisode(episodeUrlList[i]) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> episodesResponse.addEpisodeToRecycler(response) },
                { episodesResponse.onError() })
    }
}