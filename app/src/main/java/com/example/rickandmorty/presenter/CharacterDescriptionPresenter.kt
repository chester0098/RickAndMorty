package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.episode.Episode
import com.example.rickandmorty.model.repository.EpisodesRepository
import com.example.rickandmorty.view.description.CharacterDescriptionView
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class CharacterDescriptionPresenter : MvpPresenter<CharacterDescriptionView>(), EpisodesResponse {
    private val episodesRepository: EpisodesRepository = EpisodesRepository()
    fun getEpisodeList(episodeUrlList: List<String>) {
        episodesRepository.getEpisodes(episodeUrlList, this)
    }

    override fun addEpisodeToRecycler(episode: Episode) {
        viewState.setEpisodeRecyclerAdapter(episode)
    }

    override fun onError() {
        TODO("Not yet implemented")
    }
}