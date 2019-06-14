package com.example.lodjinha.presentation.product_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lodjinha.domain.product.BookProductUseCase
import com.example.lodjinha.domain.product.GetProductUseCase
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.domain.product.ProductBooked
import com.example.lodjinha.presentation.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val bookProductUseCase: BookProductUseCase,
    private val rxSchedulers: RxSchedulers,
    val productDetail: MutableLiveData<Product> = MutableLiveData(),
    val productBooked: MutableLiveData<BookProductUseCase.Result> = MutableLiveData()
) : ViewModel() {


    private val disposables = CompositeDisposable()

    private fun handleProductList(result: GetProductUseCase.Result) {
        when (result) {
            is GetProductUseCase.Result.Success -> {
                productDetail.postValue(result.productDetail)

            }
            is GetProductUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetProductUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }

    private fun handleBookedProduct(result: BookProductUseCase.Result) {
        when (result) {
            is BookProductUseCase.Result.Success -> {
                productBooked.postValue(result)
            }
            is BookProductUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is BookProductUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }

    fun getBestSellerProduct(productId: Long) {
        disposables.add(
            getProductUseCase
                .execute(productId = productId)
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleProductList)
        )
    }

    fun bookProduct(productId: Long) {
        disposables.add(
            bookProductUseCase
                .execute(productId = productId)
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleBookedProduct)
        )
    }


    fun destroy() {
        disposables.clear()
    }

    class BestSellerViewModelFactory @Inject constructor(
        private val getProductUseCase: GetProductUseCase,
        private val bookProductUseCase: BookProductUseCase,
        private val rxSchedulers: RxSchedulers
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductDetailViewModel(
                getProductUseCase = getProductUseCase,
                bookProductUseCase = bookProductUseCase,
                rxSchedulers = rxSchedulers
            ) as T
        }

    }
}