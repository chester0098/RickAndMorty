package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode

interface EpisodesAndCharacterResponse {
    fun addCharacterInfo(result: Result)
    fun addEpisodeToRecycler(episode: Episode)
    fun onError(episodeList: List<Episode>)
}