package com.example.rickandmorty.view.description.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.presenter.MainPresenter
import com.example.rickandmorty.view.adapter.CharactersRecyclerViewAdapter
import com.example.rickandmorty.view.description.CharacterDescriptionActivity
import com.example.rickandmorty.view.main.LoadNewCharacters
import com.example.rickandmorty.view.main.MainView
import com.example.rickandmorty.view.main.StartCharacterDescriptionActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView, LoadNewCharacters,
    StartCharacterDescriptionActivity {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    lateinit var charactersRecyclerView: RecyclerView

    lateinit var recyclerAdapter: CharactersRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        charactersRecyclerView = findViewById(R.id.charactersRecyclerView)
        charactersRecyclerView.layoutManager = LinearLayoutManager(this)

        recyclerAdapter = CharactersRecyclerViewAdapter(ArrayList(), this, this)
        charactersRecyclerView.adapter = recyclerAdapter

        mainPresenter.downloadCharacters()

    }

    override fun updateRecyclerAdapter(list: List<Result>) {
        recyclerAdapter.addNewCharactersToList(list)
    }

    override fun showToast() {
        Toast.makeText(applicationContext, "Проверьте подключение к сети", Toast.LENGTH_SHORT)
    }

    override fun downloadNextPage() {
        mainPresenter.downloadCharacters()
    }

    override fun start(result: Result) {
        val intent = Intent(this, CharacterDescriptionActivity::class.java)
        intent.putExtra("Character", result)
        startActivity(intent)
    }


}