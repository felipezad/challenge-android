package com.example.lodjinha.presentation.best_seller

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lodjinha.domain.product.GetProductUseCase
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BestSellerViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val rxSchedulers: RxSchedulers,
    val bestSellerDetail: MutableLiveData<Product> = MutableLiveData()
) : ViewModel() {


    private val disposables = CompositeDisposable()

    private fun handleProductList(result: GetProductUseCase.Result) {
        when (result) {
            is GetProductUseCase.Result.Success -> {
                bestSellerDetail.postValue(result.productDetail)

            }
            is GetProductUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetProductUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }


    fun getProductList(productId: Int) {
        disposables.add(
            getProductUseCase
                .execute(productId = productId)
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleProductList)
        )
    }


    fun destroy() {
        disposables.clear()
    }

    class BestSellerViewModelFactory @Inject constructor(
        private val getProductUseCase: GetProductUseCase,
        private val rxSchedulers: RxSchedulers
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BestSellerViewModel(
                getProductUseCase = getProductUseCase,
                rxSchedulers = rxSchedulers
            ) as T
        }

    }
}