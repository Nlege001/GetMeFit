package com.example.getmefit.view.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmefit.network.repo.CreateWorkoutRepo
import com.example.getmefit.view.composables.SetRepCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutViewModel @Inject constructor(
    private val repo: CreateWorkoutRepo
) : ViewModel() {

    fun saveWorkout(date: Long, workout: List<SetRepCount>) {
        viewModelScope.launch {
            repo.saveWorkouts(date, workout)
        }
    }

}