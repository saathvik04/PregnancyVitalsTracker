package com.example.pregnancyvitalstracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals")
data class VitalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bp: String,
    val heartRate: Int,
    val weight: Float,
    val kicks: Int
)
