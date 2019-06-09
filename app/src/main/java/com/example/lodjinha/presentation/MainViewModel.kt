package com.example.lodjinha.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lodjinha.domain.product.GetProductListUseCase
import com.example.lodjinha.domain.product.Product
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val rxSchedulers: RxSchedulers,
    private val productList: MutableLiveData<List<Product>> = MutableLiveData()
) : ViewModel() {


    private val disposables = CompositeDisposable()

    private fun handleProductList(result: GetProductListUseCase.Result) {
        when (result) {
            is GetProductListUseCase.Result.Success -> {
//                productList.postValue(result.productList)

                val productList1 = result.productList
                Log.d("success", productList1.toString())
            }
            is GetProductListUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetProductListUseCase.Result.Loading -> {
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

    fun destroy() {
        disposables.clear()
    }

    class MainViewModelFactory @Inject constructor(
        private val getProductListUseCase: GetProductListUseCase,
        private val rxSchedulers: RxSchedulers
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                getProductListUseCase = getProductListUseCase,
                rxSchedulers = rxSchedulers
            ) as T
        }

    }
}