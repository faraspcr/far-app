package com.example.far_apps.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.far_apps.data.entity.TripNotesEntity

@Dao
interface TripNotesDao {
    @Query("SELECT * FROM trip_notes ORDER BY tanggalKunjungan DESC")
    suspend fun getAll(): List<TripNotesEntity>

    @Insert
    suspend fun insert(tripNotes: TripNotesEntity)

    @Update
    suspend fun update(tripNotes: TripNotesEntity)

    @Delete
    suspend fun delete(tripNotes: TripNotesEntity)
}