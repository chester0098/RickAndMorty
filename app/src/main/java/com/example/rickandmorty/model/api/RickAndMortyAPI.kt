package com.example.rickandmorty.model.api

import com.example.rickandmorty.model.entities.characters.Characters
import com.example.rickandmorty.model.entities.episode.Episode
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyAPI {
    @GET("api/character/")
    fun getCharacters(@Query("page") page: Int): Observable<Characters>

    @GET
    fun getEpisode(@Url url: String): Observable<Episode>

}