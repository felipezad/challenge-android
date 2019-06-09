package com.example.lodjinha.data.remote

import com.example.lodjinha.data.model.BasicResponse
import com.example.lodjinha.data.model.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LodjinhaApi {
    companion object {
        const val ENDPOINT: String = "https://alodjinha.herokuapp.com/"
    }

    @GET("produto")
    fun getProducts(@Query("id") artistId: String, @Query("limit") limit: Int = 5): Single<BasicResponse<ProductResponse>>
}
