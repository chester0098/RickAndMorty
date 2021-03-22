package com.example.rickandmorty.di

import com.example.rickandmorty.model.api.RickAndMortyAPI
import com.example.rickandmorty.model.db.RealmDatabase
import com.example.rickandmorty.model.repository.CharactersRepository
import com.example.rickandmorty.model.repository.EpisodesRepository
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://rickandmortyapi.com/"


val networkModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { provideRetrofit() }
}

val apiModule = module {
    fun provideNetworkApi(retrofit: Retrofit): RickAndMortyAPI {
        return retrofit.create(RickAndMortyAPI::class.java)
    }

    single { provideNetworkApi(get()) }
}

val repositoryModule = module {
    fun provideCharactersRepository(api: RickAndMortyAPI, db: RealmDatabase): CharactersRepository {
        return CharactersRepository(api, db)
    }

    fun provideEpisodesRepository(api: RickAndMortyAPI, db: RealmDatabase): EpisodesRepository {
        return EpisodesRepository(api, db)
    }

    factory { provideCharactersRepository(get(), get()) }
    factory { provideEpisodesRepository(get(), get()) }
}

var databaseModule = module {
    fun provideRealmDatabase(): RealmDatabase {
        return RealmDatabase()
    }
    single { provideRealmDatabase() }
}

