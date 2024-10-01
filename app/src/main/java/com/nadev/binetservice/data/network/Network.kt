package com.nadev.binetservice.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class Network @Inject constructor() {

    val request: API = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)

    companion object {
        const val BASE_URL = "http://shans.d2.i-partner.ru"
    }
}