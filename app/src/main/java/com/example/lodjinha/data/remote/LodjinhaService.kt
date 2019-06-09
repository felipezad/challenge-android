package com.example.lodjinha.data.remote

import com.example.lodjinha.data.CustomService
import javax.inject.Inject

class LodjinhaService @Inject constructor(private val lodjinhaApi: LodjinhaApi) :
    CustomService<LodjinhaApi> {

    override fun getServiceApi(): LodjinhaApi = lodjinhaApi
}