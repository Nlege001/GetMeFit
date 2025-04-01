package com.example.getmefit.network.di

import com.example.getmefit.network.ApiService
import com.example.getmefit.network.repo.ExercisesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideExercisesRepo(apiService: ApiService): ExercisesRepo {
        return ExercisesRepo(apiService)
    }
}