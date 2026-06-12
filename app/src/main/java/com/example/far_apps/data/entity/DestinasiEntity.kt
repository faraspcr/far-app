package com.example.far_apps.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinasi")
data class DestinasiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaDestinasi: String,
    val deskripsi: String,
    val lokasi: String,
    val hargaTiket: String,
    val gambarResId: Int = 0
)