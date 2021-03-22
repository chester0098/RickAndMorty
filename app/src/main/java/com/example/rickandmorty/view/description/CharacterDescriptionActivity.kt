package com.example.rickandmorty.view.description

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode
import com.example.rickandmorty.model.utils.DateTimeFormatUtils
import com.example.rickandmorty.presenter.CharacterDescriptionPresenter
import com.example.rickandmorty.view.adapter.EpisodesRecyclerAdapter
import com.example.rickandmorty.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_character_description.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class CharacterDescriptionActivity : MvpAppCompatActivity(), CharacterDescriptionView {
    @InjectPresenter
    lateinit var characterDescriptionPresenter: CharacterDescriptionPresenter

    private lateinit var episodesRecyclerAdapter: EpisodesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_description)
        val id: Int = intent.getIntExtra(MainActivity.ID_TAG, 0)
        episodesRecyclerAdapter = EpisodesRecyclerAdapter(ArrayList())
        episodesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        episodesRecyclerView.adapter = episodesRecyclerAdapter

        characterDescriptionPresenter.getCharacterAndEpisodes(id)

    }

    override fun setEpisodeRecyclerAdapter(episode: Episode) {
        episodesRecyclerAdapter.addEpisodeToList(episode)
    }

    override fun setCharacterInfo(result: Result) {
        Glide.with(this).load(result.image).into(characterImageView)
        nameTextView.append(result.name)
        genderTextView.append(result.gender)
        speciesTextView.append(result.species)
        statusTextView.append(result.status)
        typeTextView.append(result.type)
        createdTextView.append(DateTimeFormatUtils.format(result.created!!))
    }

    override fun showErrorToast() {
        Toast.makeText(
            applicationContext,
            getString(R.string.checkTheConnection),
            Toast.LENGTH_SHORT
        )
    }
}