package com.example.getmefit.network.data

enum class ExerciseType(
    val queryParamLabel: String,
) {
    CARDIO(
        queryParamLabel = "cardio"
    ),
    OLYMPIC_WEIGHTLIFTING(
        queryParamLabel = "olympic_weightlifting"
    ),
    PLYOMETRICS(
        queryParamLabel = "plyometrics"
    ),
    POWERLIFTING(
        queryParamLabel = "powerlifting"
    ),
    STRENGTH(
        queryParamLabel = "strength"
    ),
    STRETCHING(
        queryParamLabel = "stretching"
    ),
    STRONGMAN(
        queryParamLabel = "strongman"
    ),
}