package com.example.far_apps.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BeritaApiClient {
    // BASE_URL untuk NewsAPI
    private const val BASE_URL = "https://newsapi.org/v2/"


    val apiService: BeritaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeritaApiService::class.java)
    }
}

