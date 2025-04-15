package com.example.getmefit.view.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmefit.network.repo.CreateWorkoutRepo
import com.example.getmefit.view.composables.SetRepCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutViewModel @Inject constructor(
    private val repo: CreateWorkoutRepo,
) : ViewModel() {

    private val _saveSuccess = MutableStateFlow<Boolean?>(null)
    val saveSuccess: StateFlow<Boolean?> = _saveSuccess

    fun saveWorkout(date: Long, workout: List<SetRepCount>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.saveWorkouts(date, workout)
                _saveSuccess.value = true
            } catch (e: Exception) {
                _saveSuccess.value = false
            }
        }
    }
}