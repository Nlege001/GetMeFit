package com.example.getmefit.network.repo

import com.example.getmefit.room.WorkoutDao
import com.example.getmefit.view.data.WorkoutDetails
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class MyWorkoutRepo @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    fun getWorkouts(): Flow<List<WorkoutDetails>> {
        return workoutDao.getWorkouts().map { workoutList ->
            workoutList.map {
                WorkoutDetails(
                    it.details,
                    it.date,
                )
            }
        }
    }
}