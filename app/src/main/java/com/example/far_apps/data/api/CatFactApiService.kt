package com.example.far_apps.data.api

import com.example.far_apps.data.model.CatFactModel
import retrofit2.http.GET


interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}
