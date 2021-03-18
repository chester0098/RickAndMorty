package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.episode.Episode

interface EpisodesResponse {
    fun addEpisodeToRecycler(episode: Episode)
    fun onError()
}