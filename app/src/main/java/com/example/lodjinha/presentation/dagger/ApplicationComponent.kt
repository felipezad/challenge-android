package com.example.lodjinha.presentation.dagger

import com.example.lodjinha.presentation.best_seller.BestSellerViewModel
import com.example.lodjinha.presentation.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    val mainViewModelFactory: MainViewModel.MainViewModelFactory
    val bestSellersViewModel: BestSellerViewModel.BestSellerViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}