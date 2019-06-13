package com.example.lodjinha.presentation.dagger

import com.example.lodjinha.presentation.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    val mainViewModelFactory: MainViewModel.MainViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}