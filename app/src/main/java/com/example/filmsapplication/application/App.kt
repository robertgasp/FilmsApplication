package com.example.filmsapplication.application

import android.app.Application
import com.example.filmsapplication.di.application
import com.example.filmsapplication.di.filmsModule
import org.koin.core.context.startKoin
import java.lang.IllegalStateException

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(application, filmsModule)
        }
    }
}