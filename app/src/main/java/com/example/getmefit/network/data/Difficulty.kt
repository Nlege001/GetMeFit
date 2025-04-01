package com.example.getmefit.network.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Difficulty : Parcelable, ExerciseDetails {

    @Parcelize
    data class Beginner(
        override var isSelected: Boolean = false,
        override val queryParamLabel: String = "beginner",
        override val label: String = "Beginner"
    ) : Difficulty()

    @Parcelize
    data class Intermediate(
        override var isSelected: Boolean = false,
        override val queryParamLabel: String = "intermediate",
        override val label: String = "Intermediate"
    ) : Difficulty()

    @Parcelize
    data class Expert(
        override var isSelected: Boolean = false,
        override val queryParamLabel: String = "Expert",
        override val label: String = "expert"
    ) : Difficulty()

    companion object {

        fun getItems() : List<ExerciseDetails> {
            return listOf(
                Beginner(),
                Intermediate(),
                Expert()
            )
        }

    }
}