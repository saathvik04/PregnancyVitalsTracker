package com.example.pregnancyvitalstracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalDao {

    @Query("SELECT * FROM vitals ORDER BY id DESC")
    fun getAll(): Flow<List<VitalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vital: VitalEntity)
}
