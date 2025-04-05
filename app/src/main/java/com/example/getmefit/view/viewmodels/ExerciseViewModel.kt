package com.example.getmefit.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getmefit.common.RepoState
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.network.repo.ExercisesRepo
import com.example.getmefit.view.data.ExerciseDetailsNavData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel @AssistedInject constructor(
    private val exercisesRepo: ExercisesRepo,
    @Assisted val navData: ExerciseDetailsNavData,
) : ViewModel() {

    private val _exercisesState = MutableStateFlow<RepoState<List<Exercise>>>(RepoState.Loading)
    val exercises: StateFlow<RepoState<List<Exercise>>> = _exercisesState

    init {
        Log.d("Naol", "Naol $navData")
        getExercises(
            name = null,
            type = navData.type?.queryParamLabel,
            muscle = navData.muscle?.queryParamLabel,
            difficulty = navData.difficulty?.queryParamLabel,
        )
    }

    fun getExercises(
        name: String?,
        type: String?,
        muscle: String?,
        difficulty: String?
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

    @AssistedFactory
    interface ExerciseViewModelFactory {
        fun create(navData: ExerciseDetailsNavData): ExerciseViewModel
    }

}