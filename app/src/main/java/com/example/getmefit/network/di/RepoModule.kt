package com.example.getmefit.network.di

import com.example.getmefit.network.ApiService
import com.example.getmefit.network.repo.ExercisesRepo
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideExercisesRepo(apiService: ApiService): ExercisesRepo {
        return ExercisesRepo(apiService)
    }
}