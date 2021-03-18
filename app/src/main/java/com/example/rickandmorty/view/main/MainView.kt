package com.example.rickandmorty.view.main

import com.example.rickandmorty.model.entities.characters.Result
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface MainView : MvpView {
    fun updateRecyclerAdapter(list: List<Result>)
    fun showErrorToast()
}