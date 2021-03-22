package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.repository.CharactersRepository
import com.example.rickandmorty.view.main.MainView
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), CharacterResponse, KoinComponent {
    private var pageNum = 1;
    private var maxPageNum = 1;
    private var firstLoad = true
    private var internetAvailable = true

    private val characterRepository: CharactersRepository by inject()

    fun downloadCharacters() {
        if (pageNum <= maxPageNum && internetAvailable)
            characterRepository.getCharacters(pageNum, this)
    }

    override fun addCharacterToRecycler(listCharacters: List<Result>, maxPageNum: Int) {
        if (firstLoad) {
            this.maxPageNum = maxPageNum
            firstLoad = false
        }
        viewState.updateRecyclerAdapter(listCharacters)
        pageNum++
    }

    override fun onError(listCharacters: List<Result>) {
        internetAvailable = false
        viewState.updateRecyclerAdapter(listCharacters)
        viewState.showErrorToast()
    }
}