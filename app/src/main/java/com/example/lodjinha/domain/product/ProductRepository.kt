package com.example.lodjinha.domain.product

import com.example.lodjinha.data.remote.LodjinhaService
import javax.inject.Inject

class ProductRepository @Inject constructor(val productMapper: ProductMapper, val lodjinhaService: LodjinhaService) {
}