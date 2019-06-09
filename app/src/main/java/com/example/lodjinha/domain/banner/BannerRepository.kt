package com.example.lodjinha.domain.banner

import com.example.lodjinha.data.remote.LodjinhaService
import io.reactivex.Single
import javax.inject.Inject

class BannerRepository @Inject constructor(
    private val bannerMapper: BannerMapper,
    private val lodjinhaService: LodjinhaService
) {

    fun getBanner(): Single<List<Banner>> {
        return lodjinhaService.getServiceApi().getBanner()
            .map { bannerMapper.to(from = it.data) }
    }
}