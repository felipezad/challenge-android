package com.example.lodjinha.data.remote

import com.example.lodjinha.data.CustomService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LodjinhaService : CustomService<LodjinhaApi> {

    private val host = "https://alodjinha.herokuapp.com/"

    override fun createServiceApi(okHttpClient: OkHttpClient): LodjinhaApi {
        return Retrofit.Builder()
            .baseUrl(host)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LodjinhaApi::class.java)
    }
}