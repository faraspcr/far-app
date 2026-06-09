package com.example.far_apps.data.api

import com.example.far_apps.data.model.PhotoModel
import retrofit2.http.GET


interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}
