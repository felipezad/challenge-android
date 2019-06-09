package com.example.lodjinha.data.dagger

import com.example.lodjinha.data.remote.LodjinhaApi
import com.example.lodjinha.data.remote.LodjinhaApi.Companion.ENDPOINT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object LodjinhaServiceModule {

    @JvmStatic
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @JvmStatic
    @Provides
    fun provideLodjinhaApi(okHttpClient: OkHttpClient): LodjinhaApi = Retrofit.Builder()
        .baseUrl(ENDPOINT)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LodjinhaApi::class.java)
}