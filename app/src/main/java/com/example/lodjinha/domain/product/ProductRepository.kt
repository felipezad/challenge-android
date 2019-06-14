package com.example.lodjinha.domain.product

import com.example.lodjinha.data.remote.LodjinhaService
import io.reactivex.Single
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productMapper: ProductMapper,
    private val lodjinhaService: LodjinhaService
) {

    fun getProducts(categoriaId: Long): Single<List<Product>> {
        return lodjinhaService.getServiceApi().getProducts(categoriaId = categoriaId)
            .map { productMapper.to(from = it.data) }
    }

    fun getProductDetail(productId: Long): Single<Product> {
        return lodjinhaService.getServiceApi().getProduct(productId = productId)
            .map { productMapper.to(from = it) }
    }

    fun getBestSellers(): Single<List<Product>> {
        return lodjinhaService.getServiceApi().getBestSellers()
            .map { productMapper.to(from = it.data) }
    }
}