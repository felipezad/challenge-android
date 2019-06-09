package com.example.lodjinha.domain.banner

import com.example.lodjinha.data.model.BannerResponse
import com.exercise.musicshuffle.domain.Mapper
import javax.inject.Inject


class BannerMapper @Inject constructor() : Mapper<BannerResponse, Banner> {
    override fun to(from: BannerResponse): Banner {
        return from.run {
            Banner(
                id = this.id,
                urlImagem = this.urlImagem,
                linkUrl = this.linkUrl
            )
        }
    }
}