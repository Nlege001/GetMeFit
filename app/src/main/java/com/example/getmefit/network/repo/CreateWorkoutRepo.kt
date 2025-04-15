package com.example.getmefit.network.repo

import com.example.getmefit.room.WorkoutDao
import com.example.getmefit.room.WorkoutDetailEntity
import com.example.getmefit.view.composables.SetRepCount
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateWorkoutRepo @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    suspend fun saveWorkouts(
        date: Long,
        workout: List<SetRepCount>
    ) {
        workoutDao.addWorkout(WorkoutDetailEntity(date = date, details = workout))
    }
}