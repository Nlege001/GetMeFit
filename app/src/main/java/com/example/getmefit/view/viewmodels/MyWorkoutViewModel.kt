package com.example.getmefit.view.viewmodels

import androidx.lifecycle.ViewModel
import com.example.getmefit.network.repo.MyWorkoutRepo
import com.example.getmefit.view.composables.SetRepCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MyWorkoutViewModel @Inject constructor(
    myWorkoutRepo: MyWorkoutRepo
) : ViewModel() {
    val workouts: Flow<Map<Long, List<SetRepCount>>> = myWorkoutRepo.getWorkouts()
}