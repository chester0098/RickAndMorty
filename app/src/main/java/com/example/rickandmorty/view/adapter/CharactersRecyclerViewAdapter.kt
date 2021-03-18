package com.example.rickandmorty.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.view.main.LoadNewCharacters
import com.example.rickandmorty.view.main.StartCharacterDescriptionActivity

class CharactersRecyclerViewAdapter(
    private val characters: ArrayList<Result>,
    private val loadNewCharacters: LoadNewCharacters,
    private val startCharacterDescriptionActivity: StartCharacterDescriptionActivity
) :
    RecyclerView.Adapter<CharactersRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(itemView)
    }

    fun addNewCharactersToList(newList: List<Result>) {
        characters.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(characters[position], position, startCharacterDescriptionActivity)
    }

    override fun getItemCount() = characters.size

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition
        if (layoutPosition == characters.size - 5) {
            loadNewCharacters.downloadNextPage()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var characterName: TextView? = null
        var characterImage: ImageView? = null
        var cardView: CardView? = null

        fun bind(
            item: Result,
            position: Int,
            startCharacterDescriptionActivity: StartCharacterDescriptionActivity
        ) {
            characterName?.text = item.name
            Glide.with(itemView.context)
                .load(item.image)
                .into(characterImage!!)

            cardView?.setOnClickListener { startCharacterDescriptionActivity.start(item) }
        }

        init {
            characterName = itemView.findViewById(R.id.episodeName)
            characterImage = itemView.findViewById(R.id.characterImage)
            cardView = itemView.findViewById(R.id.cardView)
        }
    }
}