package com.example.far_apps.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.far_apps.data.dao.DestinasiDao
import com.example.far_apps.data.dao.TripNotesDao
import com.example.far_apps.data.entity.DestinasiEntity
import com.example.far_apps.data.entity.TripNotesEntity

@Database(
    entities = [DestinasiEntity::class, TripNotesEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun destinasiDao(): DestinasiDao
    abstract fun tripNotesDao(): TripNotesDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "far_apps_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}