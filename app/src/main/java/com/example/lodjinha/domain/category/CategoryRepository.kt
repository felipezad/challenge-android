package com.example.lodjinha.domain.category

import com.example.lodjinha.data.remote.LodjinhaService
import io.reactivex.Single
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryMapper: CategoryMapper,
    private val lodjinhaService: LodjinhaService
) {

    fun getCategories(): Single<List<Category>> {
        return lodjinhaService.getServiceApi().getCategories()
            .map { categoryMapper.to(from = it.data) }
    }
}