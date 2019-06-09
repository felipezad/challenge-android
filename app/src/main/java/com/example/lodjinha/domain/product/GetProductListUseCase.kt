package com.example.lodjinha.domain.product

import io.reactivex.Observable
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val productRepository: ProductRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val productList: List<Product>) : Result()
        data class Failure(val failure: Throwable) : Result()
    }

    fun execute(categoriaId: Int): Observable<Result> {
        return productRepository.getProducts(categoriaId = categoriaId)
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}