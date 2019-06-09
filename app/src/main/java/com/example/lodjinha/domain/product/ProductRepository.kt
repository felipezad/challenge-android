package com.example.lodjinha.domain.product

import com.example.lodjinha.data.remote.LodjinhaService
import io.reactivex.Single
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productMapper: ProductMapper,
    private val lodjinhaService: LodjinhaService
) {

    fun getProducts(categoriaId: Int): Single<List<Product>> {
        return lodjinhaService.getServiceApi().getProducts(categoriaId = categoriaId)
            .map { productMapper.to(from = it.data) }
    }
    fun getMostProductsSold(): Single<List<Product>> {
        return lodjinhaService.getServiceApi().getMostProductsSold()
            .map { productMapper.to(from = it.data) }
    }
}