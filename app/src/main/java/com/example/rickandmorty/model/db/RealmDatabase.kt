package com.example.rickandmorty.model.db

import com.example.rickandmorty.model.entities.characters.Result
import com.example.rickandmorty.model.entities.episode.Episode
import io.realm.Realm

class RealmDatabase {
    private lateinit var realm: Realm

    fun addCharactersToRealm(resultList: List<Result>) {
        realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { realm ->
            realm.copyToRealmOrUpdate(resultList)
        }
        realm.close()
    }

    fun getCharactersFromRealm(): List<Result> {
        realm = Realm.getDefaultInstance()
        val realmResults = realm.where(Result::class.java)?.findAllAsync()
        return realm.copyFromRealm(realmResults)
    }

    fun getOneCharacterFromRealm(id: Int): Result {
        realm = Realm.getDefaultInstance()
        val realmResults = realm.where(Result::class.java).equalTo("id", id).findFirst()
        return realm.copyFromRealm(realmResults!!)
    }

    fun addEpisodesToRealm(episode: Episode) {
        realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { realm -> realm.copyToRealmOrUpdate(episode) }
        realm.close()
    }

    fun getEpisodesFromRealm(urlListEpisodes: List<String>): List<Episode> {
        val episodeList: ArrayList<Episode> = ArrayList()
        realm = Realm.getDefaultInstance()
        urlListEpisodes.forEach {
            val realmResults = realm.where(Episode::class.java).equalTo("url", it).findFirst()
            if (realmResults != null)
                episodeList.add(realm.copyFromRealm(realmResults))
        }
        return episodeList
    }
}