package com.example.far_apps.data.api

import com.example.far_apps.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface BeritaApiService {


    // Endpoint untuk berita utama (top-headlines)
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = "3f6d2fbad72a472692d5d2a48cc4f3ab"
    ): NewsResponse
}

