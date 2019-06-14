package com.example.lodjinha.presentation.product_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lodjinha.domain.product.GetProductListUseCase
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val rxSchedulers: RxSchedulers,
    val productListUseCase: MutableLiveData<List<Product>> = MutableLiveData()
) : ViewModel() {


    private val disposables = CompositeDisposable()

    private fun handleProductList(result: GetProductListUseCase.Result) {
        when (result) {
            is GetProductListUseCase.Result.Success -> {
                productListUseCase.postValue(result.productList)

            }
            is GetProductListUseCase.Result.Failure -> {
                Log.d("error", "Error")
            }
            is GetProductListUseCase.Result.Loading -> {
                Log.d("loading", "Loading")
            }
        }
    }


    fun getListProductByCategory(categoryId: Long) {
        disposables.add(
            getProductListUseCase
                .execute(categoriaId = categoryId)
                .subscribeOn(rxSchedulers.ioThread)
                .observeOn(rxSchedulers.androidMainThread)
                .subscribe(this::handleProductList)
        )
    }


    fun destroy() {
        disposables.clear()
    }

    class ProductListViewModelFactory @Inject constructor(
        private val getProductListUseCase: GetProductListUseCase,
        private val rxSchedulers: RxSchedulers
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductListViewModel(
                getProductListUseCase = getProductListUseCase,
                rxSchedulers = rxSchedulers
            ) as T
        }

    }
}