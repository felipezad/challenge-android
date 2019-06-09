package com.example.lodjinha.data.dagger

import com.example.lodjinha.data.remote.LodjinhaService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LodjinhaServiceModule::class])
interface LodjinhaServiceComponent {

    val lodjinhaService: LodjinhaService

    @Component.Factory
    interface Factory {
        fun create(): LodjinhaServiceComponent
    }
}