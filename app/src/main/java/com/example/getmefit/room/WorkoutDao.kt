package com.example.getmefit.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts")
    fun getWorkouts(): Flow<List<WorkoutDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkout(workoutDetailEntity: WorkoutDetailEntity)
}