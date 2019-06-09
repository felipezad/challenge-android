package com.example.lodjinha.domain.product

import com.example.lodjinha.data.remote.LodjinhaService
import io.reactivex.Single
import javax.inject.Inject

class ProductRepository @Inject constructor(val productMapper: ProductMapper, val lodjinhaService: LodjinhaService) {

    fun getProducts(): Single<List<Product>> {
        return lodjinhaService.getServiceApi().getProducts(categoriaId = 4)
            .map { productMapper.to(from = it.data) }
    }
}