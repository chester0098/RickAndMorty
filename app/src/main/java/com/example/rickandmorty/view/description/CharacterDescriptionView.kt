package com.example.rickandmorty.view.description

import com.example.rickandmorty.model.entities.episode.Episode
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface CharacterDescriptionView : MvpView {
    fun setEpisodeRecyclerAdapter(episode: Episode)
    fun showErrorToast()
}