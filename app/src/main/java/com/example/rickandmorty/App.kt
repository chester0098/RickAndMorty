package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.apiModule
import com.example.rickandmorty.di.databaseModule
import com.example.rickandmorty.di.networkModule
import com.example.rickandmorty.di.repositoryModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(networkModule, apiModule, repositoryModule, databaseModule))
        }
        Realm.init(this)
    }
}