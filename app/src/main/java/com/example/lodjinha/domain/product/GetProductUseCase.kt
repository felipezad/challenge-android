package com.example.lodjinha.domain.product

import com.exercise.musicshuffle.domain.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productRepository: ProductRepository) : UseCase {

    sealed class Result {
        object Loading : Result()
        data class Success(val productDetail: Product) : Result()
        data class Failure(val failure: Throwable) : Result()
    }

    fun execute(productId: Long): Observable<Result> {
        return productRepository.getProductDetail(productId = productId)
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}