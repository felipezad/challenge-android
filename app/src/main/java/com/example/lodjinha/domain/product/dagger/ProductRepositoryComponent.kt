package com.example.lodjinha.domain.product.dagger

import com.example.lodjinha.data.dagger.LodjinhaServiceModule
import com.example.lodjinha.domain.product.ProductRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [LodjinhaServiceModule::class])
interface ProductRepositoryComponent {

    val productRepository : ProductRepository

    @Component.Factory
    interface Factory {
        fun create(): ProductRepositoryComponent
    }
}