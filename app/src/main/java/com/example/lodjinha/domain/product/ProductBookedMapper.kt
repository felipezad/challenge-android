package com.example.lodjinha.domain.product

import com.example.lodjinha.data.model.BookResponse
import com.exercise.musicshuffle.domain.Mapper
import javax.inject.Inject


class ProductBookedMapper @Inject constructor() : Mapper<BookResponse, ProductBooked> {
    override fun to(from: BookResponse): ProductBooked {
        return from.run {
            ProductBooked(
                result = this.result
            )
        }
    }
}