package com.example.getmefit.network.repo

import com.example.getmefit.room.WorkoutDao
import com.example.getmefit.view.composables.SetRepCount
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class MyWorkoutRepo @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    fun getWorkouts(): Flow<Map<Long, List<SetRepCount>>> {
        return workoutDao.getWorkouts().map { workoutList ->
            workoutList
                .groupBy { it.date }
                .mapValues {
                    it.value.flatMap { it.details }
                }
        }
    }
}