package com.example.rickandmorty.model.entities.characters

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.RawValue

open class Result(
    @PrimaryKey
    var id: Int = 0,
    var created: String? = null,
    var episode: @RawValue RealmList<String>? = null,
    var gender: String? = null,
    var image: String? = null,
    var name: String? = null,
    var species: String? = null,
    var status: String? = null,
    var type: String? = null,
) : RealmObject()