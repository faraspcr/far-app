package com.example.far_apps.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.far_apps.data.entity.DestinasiEntity

@Dao
interface DestinasiDao {
    @Query("SELECT * FROM destinasi")
    suspend fun getAll(): List<DestinasiEntity>

    @Insert
    suspend fun insert(destinasi: DestinasiEntity)

    @Update
    suspend fun update(destinasi: DestinasiEntity)

    @Delete
    suspend fun delete(destinasi: DestinasiEntity)
}