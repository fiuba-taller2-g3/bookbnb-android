package com.fiuba.bookbnb.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "https://facade-server-develop.herokuapp.com/"

    fun buildRetrofitClient(): BookbnbAPIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookbnbAPIService::class.java)
    }
}