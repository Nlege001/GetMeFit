package com.example.getmefit.network

import com.example.getmefit.network.data.Exercise
import com.example.stocktracker.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET
    fun getExercise(
        @Query("name") name: String? = null,
        @Query("type") type: String? = null,
        @Query("muscle") muscle: String? = null,
        @Query("difficulty") difficulty: String? = null,
        @Header("X-Api-Key") apiKey: String = BuildConfig.POLYGON_API_KEY
    ): Response<Exercise>
}