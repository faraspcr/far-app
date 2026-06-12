package com.example.far_apps.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_notes")
data class TripNotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val isiCatatan: String,
    val tempatWisata: String,
    val tanggalKunjungan: Long,
    val rating: Int,
    val tips: String
)