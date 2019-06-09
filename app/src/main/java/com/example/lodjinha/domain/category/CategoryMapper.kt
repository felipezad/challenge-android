package com.example.lodjinha.domain.category

import com.example.lodjinha.data.model.CategoryResponse
import com.exercise.musicshuffle.domain.Mapper
import javax.inject.Inject


class CategoryMapper @Inject constructor() : Mapper<CategoryResponse, Category> {
    override fun to(from: CategoryResponse): Category {
        return from.run {
            Category(
                id = this.id,
                urlImagem = this.urlImagem,
                descricao = this.descricao
            )
        }
    }
}