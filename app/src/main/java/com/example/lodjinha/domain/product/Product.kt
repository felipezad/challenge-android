package com.example.lodjinha.domain.product

import com.example.lodjinha.domain.category.Category

data class Product (
    val categoria : Category,
    val descricao : String,
    val id  : Long,
    val precoDe: Double,
    val precoPor: Double,
    val urlImagem : String
)