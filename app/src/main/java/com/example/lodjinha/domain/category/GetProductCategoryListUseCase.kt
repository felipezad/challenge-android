package com.example.lodjinha.domain.category

import com.exercise.musicshuffle.domain.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetProductCategoryListUseCase @Inject constructor(private val categoryRepository: CategoryRepository) : UseCase {

    sealed class Result {
        object Loading : Result()
        data class Success(val categoryList: List<Category>) : Result()
        data class Failure(val failure: Throwable) : Result()
    }

    fun execute(): Observable<Result> {
        return categoryRepository.getCategories()
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}