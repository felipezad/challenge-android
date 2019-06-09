package com.example.lodjinha.data

interface CustomService<out T> {

    fun getServiceApi(): T
}