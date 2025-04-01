package com.example.getmefit.network.data

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

sealed class ExerciseType : Parcelable, ExerciseDetails {
    @Parcelize
    data class Cardio(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "cardio"

        @IgnoredOnParcel
        override val label: String = "Cardio"
    }

    @Parcelize
    data class OlympicWeightlifting(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "olympic_weightlifting"

        @IgnoredOnParcel
        override val label: String = "Olympic Weightlifting"
    }

    @Parcelize
    data class Plyometrics(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "plyometrics"

        @IgnoredOnParcel
        override val label: String = "Plyometrics"
    }

    @Parcelize
    data class Powerlifting(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "powerlifting"

        @IgnoredOnParcel
        override val label: String = "Powerlifting"
    }

    @Parcelize
    data class Strength(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "strength"

        @IgnoredOnParcel
        override val label: String = "Strength"
    }

    @Parcelize
    data class Stretching(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "stretching"

        @IgnoredOnParcel
        override val label: String = "Stretching"
    }

    @Parcelize
    data class Strongman(override var isSelected: Boolean = false) : ExerciseType() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "strongman"

        @IgnoredOnParcel
        override val label: String = "Strongman"
    }

    companion object {

        fun getItems(): List<ExerciseDetails> {
            return listOf(
                Cardio(),
                OlympicWeightlifting(),
                Plyometrics(),
                Powerlifting(),
                Strength(),
                Stretching(),
                Strongman()
            )
        }

    }
}