package com.example.getmefit.view.data

import androidx.annotation.Keep

@Keep
enum class ExerciseOptions(
    val label: String,
    val subtitle: String,
    val isEnabled: Boolean,
    val hasAddRemoveOption: Boolean,
) {
    LOOK_UP(
        label = "Look up exercises",
        subtitle = "Look up exercise by filtering for muscles targets, exercise type and fitness level",
        isEnabled = true,
        hasAddRemoveOption = false,
    ),
    RECORD_WORKOUT(
        label = "Record a workout",
        subtitle = "Look up exercises, save and track your workouts",
        isEnabled = true,
        hasAddRemoveOption = true,
    )
}