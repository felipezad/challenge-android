package com.example.lodjinha.presentation.dagger

import com.example.lodjinha.presentation.product_detail.ProductDetailViewModel
import com.example.lodjinha.presentation.main.MainViewModel
import com.example.lodjinha.presentation.product_list.ProductListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    val mainViewModelFactory: MainViewModel.MainViewModelFactory
    val productDetailViewModel: ProductDetailViewModel.BestSellerViewModelFactory
    val productListViewModel: ProductListViewModel.ProductListViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}