package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode
import com.example.rickandmorty.model.repository.EpisodesRepository
import com.example.rickandmorty.view.description.CharacterDescriptionView
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
@InjectViewState
class CharacterDescriptionPresenter : MvpPresenter<CharacterDescriptionView>(),
    EpisodesAndCharacterResponse,
    KoinComponent {

    private val episodesRepository: EpisodesRepository by inject()

    fun getCharacterAndEpisodes(id: Int) {
        episodesRepository.getCharacterAndEpisode(id, this)
    }

    override fun addCharacterInfo(result: Result) {
        viewState.setCharacterInfo(result)
    }

    override fun addEpisodeToRecycler(episode: Episode) {
        viewState.setEpisodeRecyclerAdapter(episode)
    }

    override fun onError(episodeList: List<Episode>) {
        episodeList.forEach {
            viewState.setEpisodeRecyclerAdapter(it)
        }
        viewState.showErrorToast()
    }
}