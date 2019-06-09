package com.example.lodjinha.presentation

import android.app.Application
import com.example.lodjinha.presentation.dagger.ApplicationComponent
import com.example.lodjinha.presentation.dagger.DaggerApplicationComponent

class MyApplication : Application(), ComponentProvider<ApplicationComponent> {
    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .factory()
            .create()
    }
}