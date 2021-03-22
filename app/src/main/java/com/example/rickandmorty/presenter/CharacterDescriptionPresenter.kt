package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode
import com.example.rickandmorty.model.repository.CharactersRepository
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
    EpisodesResponse,
    KoinComponent {

    private val episodesRepository: EpisodesRepository by inject()
    private val characterRepository: CharactersRepository by inject()

    fun getCharacterAndEpisodes(id: Int) {
        val result: Result = characterRepository.getCharacterFromDB(id)
        addCharacterInfo(result)
        episodesRepository.getCharacterAndEpisode(result.episode!!, this)
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