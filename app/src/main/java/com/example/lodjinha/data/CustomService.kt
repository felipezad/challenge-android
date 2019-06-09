package com.example.lodjinha.data

import okhttp3.OkHttpClient

interface CustomService<out T> {

    fun createServiceApi(okHttpClient: OkHttpClient): T
}