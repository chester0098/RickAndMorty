package com.example.rickandmorty.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.entities.episode.Episode

class EpisodesRecyclerAdapter(private var episodesList: ArrayList<Episode>) :
    RecyclerView.Adapter<EpisodesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(episodesList[position], position)
    }

    fun addEpisodeToList(newList: Episode) {
        episodesList.add(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = episodesList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var episodeNumber: TextView? = null

        fun bind(
            item: Episode,
            position: Int
        ) {
            episodeNumber?.text = item.episode
        }

        init {
            episodeNumber = itemView.findViewById(R.id.episodeNumber)
        }
    }
}