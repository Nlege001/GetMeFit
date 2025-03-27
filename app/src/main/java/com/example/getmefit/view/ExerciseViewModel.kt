package com.example.getmefit.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmefit.common.RepoState
import com.example.getmefit.network.data.Difficulty
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.network.data.ExerciseType
import com.example.getmefit.network.data.Muscle
import com.example.getmefit.network.repo.ExercisesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exercisesRepo: ExercisesRepo
) : ViewModel() {

    private val _exercisesState = MutableStateFlow<RepoState<Exercise>>(RepoState.Loading)
    val exercises: StateFlow<RepoState<Exercise>> = _exercisesState

    /*init {
        getExercises()
    }*/

    fun getExercises(
        name: String?,
        type: ExerciseType?,
        muscle: Muscle?,
        difficulty: Difficulty?
    ) {
        viewModelScope.launch {
            _exercisesState.value = exercisesRepo.fetchExercises(
                name = name,
                type = type,
                muscle = muscle,
                difficulty = difficulty
            )
        }
    }
}