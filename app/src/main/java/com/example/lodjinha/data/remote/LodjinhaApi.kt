package com.example.lodjinha.data.remote

import com.example.lodjinha.data.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LodjinhaApi {
    companion object {
        const val ENDPOINT: String = "https://alodjinha.herokuapp.com/"
    }

    @GET("produto")
    fun getProducts(
        @Query("categoriaId") categoriaId: Int,
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int = 0
    ): Single<BasicResponse<ProductResponse>>

    @GET("produto/{productId}")
    fun getProduct(@Path("productId") productId: Int): Single<ProductResponse>

    @POST("produto/{productId}")
    fun bookProduct(@Path("productId") productId: Int): Single<BookResponse>

    @GET("produto/maisvendidos")
    fun getMostSoldProducts(): Single<BasicResponse<ProductResponse>>

    @GET("banner")
    fun getBanner(): Single<BasicResponse<BannerResponse>>

    @GET("categoria")
    fun getCategories(): Single<BasicResponse<CategoryResponse>>
}
