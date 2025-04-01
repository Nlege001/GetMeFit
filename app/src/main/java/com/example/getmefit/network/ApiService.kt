package com.example.getmefit.network

import com.example.getmefit.network.data.Exercise
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/exercises")
    suspend fun getExercise(
        @Query("name") name: String? = null,
        @Query("type") type: String? = null,
        @Query("muscle") muscle: String? = null,
        @Query("difficulty") difficulty: String? = null,
    ): Response<List<Exercise>>
}