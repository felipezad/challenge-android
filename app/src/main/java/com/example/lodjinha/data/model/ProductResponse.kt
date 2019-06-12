package com.example.lodjinha.data.model

data class ProductResponse(
    val nome : String,
    val categoria: CategoryResponse,
    val descricao: String,
    val id: Long,
    val precoDe: Double,
    val precoPor: Double,
    val urlImagem: String
)