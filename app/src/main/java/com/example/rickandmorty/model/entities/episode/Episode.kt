package com.example.rickandmorty.model.entities.episode

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Episode(
    @PrimaryKey
    var url: String? = null,
    var episode: String? = null,
    var name: String? = null
) : RealmObject()