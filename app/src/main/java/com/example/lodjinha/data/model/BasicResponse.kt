package com.example.lodjinha.data.model

data class BasicResponse<T>(val data: List<T>, val offset: Int, val total: Int)