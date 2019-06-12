package com.example.lodjinha.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lodjinha.domain.banner.Banner
import com.example.lodjinha.domain.banner.GetBannerUseCase
import com.example.lodjinha.domain.category.Category
import com.example.lodjinha.domain.category.GetProductCategoryListUseCase
import com.example.lodjinha.domain.product.GetBestSellersSoldListUseCase
import com.example.lodjinha.domain.product.GetProductListUseCase
import com.example.lodjinha.domain.product.Product
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    private val getProductCategoryListUseCase: GetProductCategoryListUseCase,
    private val getBestSellersSoldListUseCase: GetBestSellersSoldListUseCase,
    private val rxSchedulers: RxSchedulers,
    val productList: MutableLiveData<List<Product>> = MutableLiveData(),
    val bestSellersList: MutableLiveData<List<Product>> = MutableLiveData(),
    val banner: MutableLiveData<List<Banner>> = MutableLiveData(),
    val productCategories: MutableLiveData<List<Category>> = MutableLiveData()
) : ViewModel() {


    private val disposables = CompositeDisposable()

    private fun handleProductList(result: GetProductListUseCase.Result) {
        when (result) {
            is GetProductListUseCase.Result.Success -> {
                productList.postValue(result.productList)

            }
            is GetProductListUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetProductListUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }

    private fun handleBestSellersList(result: GetBestSellersSoldListUseCase.Result) {
        when (result) {
            is GetBestSellersSoldListUseCase.Result.Success -> {
                bestSellersList.postValue(result.productList)

            }
            is GetBestSellersSoldListUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetBestSellersSoldListUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }

    private fun handleBanner(result: GetBannerUseCase.Result) {
        when (result) {
            is GetBannerUseCase.Result.Success -> {
                banner.postValue(result.bannerList)

            }
            is GetBannerUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetBannerUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }

    private fun handleProductCategory(result: GetProductCategoryListUseCase.Result) {
        when (result) {
            is GetProductCategoryListUseCase.Result.Success -> {
                productCategories.postValue(result.categoryList)

            }
            is GetProductCategoryListUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetProductCategoryListUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }

    fun getProductList(categoriaId: Int) {
        disposables.add(
            getProductListUseCase
                .execute(categoriaId = categoriaId)
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleProductList)
        )
    }

    fun getBestSellersList() {
        disposables.add(
            getBestSellersSoldListUseCase
                .execute()
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleBestSellersList)
        )
    }

    fun getBanner() {
        disposables.add(
            getBannerUseCase
                .execute()
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleBanner)
        )
    }

    fun getProductCategory() {
        disposables.add(
            getProductCategoryListUseCase
                .execute()
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleProductCategory)
        )
    }

    fun destroy() {
        disposables.clear()
    }

    class MainViewModelFactory @Inject constructor(
        private val getProductListUseCase: GetProductListUseCase,
        private val getBannerUseCase: GetBannerUseCase,
        private val getProductCategoryListUseCase: GetProductCategoryListUseCase,
        private val getBestSellersSoldListUseCase: GetBestSellersSoldListUseCase,
        private val rxSchedulers: RxSchedulers
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                getProductListUseCase = getProductListUseCase,
                getBannerUseCase = getBannerUseCase,
                getProductCategoryListUseCase = getProductCategoryListUseCase,
                getBestSellersSoldListUseCase = getBestSellersSoldListUseCase,
                rxSchedulers = rxSchedulers
            ) as T
        }

    }
}