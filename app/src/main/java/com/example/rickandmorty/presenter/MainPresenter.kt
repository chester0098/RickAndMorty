package com.example.rickandmorty.presenter

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.repository.CharactersRepository
import com.example.rickandmorty.view.main.MainView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), CharacterResponse {
    private var pageNum = 1;
    private val characterRepository: CharactersRepository = CharactersRepository()
    fun downloadCharacters() {
        characterRepository.getCharacters(pageNum, this)
    }

    override fun addCharacterToRecycler(listCharacters: List<Result>) {
        viewState.updateRecyclerAdapter(listCharacters)
        pageNum++
    }

    override fun onError() {
        viewState.showErrorToast()
    }


}