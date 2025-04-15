package com.example.getmefit.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.getmefit.view.composables.SetRepCount

@Entity(tableName = "workouts")
data class WorkoutDetailEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val details: List<SetRepCount>
)