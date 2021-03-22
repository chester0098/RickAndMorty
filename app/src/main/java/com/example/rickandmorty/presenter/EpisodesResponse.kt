package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode

interface EpisodesResponse {
    fun addCharacterInfo(result: Result)
    fun addEpisodeToRecycler(episode: Episode)
    fun onError(episodeList: List<Episode>)
}