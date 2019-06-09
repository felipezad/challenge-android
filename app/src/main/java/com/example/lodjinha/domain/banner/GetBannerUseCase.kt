package com.example.lodjinha.domain.banner

import com.exercise.musicshuffle.domain.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(private val bannerRepository: BannerRepository) : UseCase {

    sealed class Result {
        object Loading : Result()
        data class Success(val bannerList: List<Banner>) : Result()
        data class Failure(val failure: Throwable) : Result()
    }

    fun execute(): Observable<Result> {
        return bannerRepository.getBanner()
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}