package com.example.pregnancyvitalstracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [VitalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class VitalDatabase : RoomDatabase() {

    abstract fun vitalDao(): VitalDao

    companion object {
        @Volatile
        private var INSTANCE: VitalDatabase? = null

        fun getDatabase(context: Context): VitalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VitalDatabase::class.java,
                    "vital_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
