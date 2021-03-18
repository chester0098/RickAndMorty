package com.example.rickandmorty.view.description

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode
import com.example.rickandmorty.model.utils.DateTimeFormatUtils
import com.example.rickandmorty.presenter.CharacterDescriptionPresenter
import com.example.rickandmorty.view.adapter.EpisodesRecyclerAdapter
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
        val result: Result = intent.getSerializableExtra("Character") as Result

        Glide.with(this).load(result.image).into(characterImageView)
        nameTextView.text = result.name
        genderTextView.text = result.gender
        speciesTextView.text = result.species
        statusTextView.text = result.status
        typeTextView.text = result.type
        createdTextView.text = DateTimeFormatUtils.format(result.created)

        episodesRecyclerAdapter = EpisodesRecyclerAdapter(ArrayList())
        episodesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        episodesRecyclerView.adapter = episodesRecyclerAdapter

        characterDescriptionPresenter.getEpisodeList(result.episode)

    }

    override fun setEpisodeRecyclerAdapter(episode: Episode) {
        episodesRecyclerAdapter.addEpisodeToList(episode)
    }
}