package com.example.getmefit.network.repo

import com.example.getmefit.common.RepoState
import com.example.getmefit.common.fetch
import com.example.getmefit.network.ApiService
import com.example.getmefit.network.data.Difficulty
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.network.data.ExerciseType
import com.example.getmefit.network.data.Muscle
import javax.inject.Inject

class ExercisesRepo @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchExercises(
        name: String?,
        type: String?,
        muscle: String?,
        difficulty: String?
    ): RepoState<List<Exercise>> {
        return fetch {
            apiService.getExercise(
                name = name,
                type = type,
                muscle = muscle,
                difficulty = difficulty
            )
        }

    }
}