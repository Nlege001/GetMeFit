package com.example.getmefit.view.viewmodels

import androidx.lifecycle.ViewModel
import com.example.getmefit.network.repo.MyWorkoutRepo
import com.example.getmefit.room.WorkoutDetailEntity
import com.example.getmefit.view.data.WorkoutDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MyWorkoutViewModel @Inject constructor(
    private val myWorkoutRepo: MyWorkoutRepo
): ViewModel() {
    val workouts : Flow<List<WorkoutDetails>> = myWorkoutRepo.getWorkouts()
}