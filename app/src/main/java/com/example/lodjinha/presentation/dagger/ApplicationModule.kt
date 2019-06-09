package com.example.lodjinha.presentation.dagger

import com.example.lodjinha.data.remote.LodjinhaApi
import com.example.lodjinha.data.remote.LodjinhaApi.Companion.ENDPOINT
import com.example.lodjinha.presentation.RxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
object ApplicationModule {

    @JvmStatic
    @Provides
    @Named(RxSchedulers.IO)
    fun provideSchedulersIO(): Scheduler = Schedulers.io()

    @JvmStatic
    @Provides
    @Named(RxSchedulers.MAIN)
    fun provideMainThread(): Scheduler = AndroidSchedulers.mainThread()

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