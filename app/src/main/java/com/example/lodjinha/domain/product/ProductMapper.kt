package com.example.lodjinha.domain.product

import com.example.lodjinha.data.model.CategoryResponse
import com.example.lodjinha.data.model.ProductResponse
import com.example.lodjinha.domain.category.Category
import com.exercise.musicshuffle.domain.Mapper
import javax.inject.Inject


class ProductMapper @Inject constructor() : Mapper<ProductResponse, Product> {
    override fun to(from: ProductResponse): Product {
        return from.run {
            Product(
                id = this.id,
                nome = this.nome,
                categoria = this.categoria.toCategory(),
                descricao = this.descricao,
                precoDe = this.precoDe,
                precoPor = this.precoPor,
                urlImagem = this.urlImagem
            )
        }
    }
}

fun CategoryResponse.toCategory(): Category {
    return this.run {
        Category(
            descricao = this.descricao,
            id = this.id,
            urlImagem = this.urlImagem
        )
    }
}