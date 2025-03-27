package com.example.getmefit.network.data

enum class Difficulty(
    val queryParamLabel: String,
) {
    BEGINNER(
        queryParamLabel = "beginner"
    ),
    INTERMEDIATE(
        queryParamLabel = "intermediate"
    ),
    EXPERT(
        queryParamLabel = "expert"
    )
}