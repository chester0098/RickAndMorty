package com.example.rickandmorty.model.retrofit

import com.example.rickandmorty.model.api.RickAndMortyAPI
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://rickandmortyapi.com/"

    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    val rickAndMortyAPI: RickAndMortyAPI
        get() = getClient().create(RickAndMortyAPI::class.java)
}