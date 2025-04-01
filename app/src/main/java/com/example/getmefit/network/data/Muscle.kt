package com.example.getmefit.network.data

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

sealed class Muscle : ExerciseDetails, Parcelable {
    @Parcelize
    data class Abdominals(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "abdominals"

        @IgnoredOnParcel
        override val label: String = "Abdominals"
    }

    @Parcelize
    data class Abductors(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "abductors"

        @IgnoredOnParcel
        override val label: String = "Abductors"
    }

    @Parcelize
    data class Adductors(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "adductors"

        @IgnoredOnParcel
        override val label: String = "Adductors"
    }

    @Parcelize
    data class Biceps(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "biceps"

        @IgnoredOnParcel
        override val label: String = "Biceps"
    }

    @Parcelize
    data class Calves(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "calves"

        @IgnoredOnParcel
        override val label: String = "Calves"
    }

    @Parcelize
    data class Chest(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "chest"

        @IgnoredOnParcel
        override val label: String = "Chest"
    }

    @Parcelize
    data class Forearms(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "forearms"

        @IgnoredOnParcel
        override val label: String = "Forearms"
    }

    @Parcelize
    data class Glutes(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "glutes"

        @IgnoredOnParcel
        override val label: String = "Glutes"
    }

    @Parcelize
    data class Hamstrings(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "hamstrings"

        @IgnoredOnParcel
        override val label: String = "Hamstrings"
    }

    @Parcelize
    data class Lats(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "lats"

        @IgnoredOnParcel
        override val label: String = "Lats"
    }

    @Parcelize
    data class LowerBack(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "lower_back"

        @IgnoredOnParcel
        override val label: String = "Lower Back"
    }

    @Parcelize
    data class MiddleBack(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "middle_back"

        @IgnoredOnParcel
        override val label: String = "Middle Back"
    }

    @Parcelize
    data class Neck(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "neck"

        @IgnoredOnParcel
        override val label: String = "Neck"
    }

    @Parcelize
    data class Quadriceps(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "quadriceps"

        @IgnoredOnParcel
        override val label: String = "Quadriceps"
    }

    @Parcelize
    data class Traps(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "traps"

        @IgnoredOnParcel
        override val label: String = "Traps"
    }

    @Parcelize
    data class Triceps(override var isSelected: Boolean = false) : Muscle() {
        @IgnoredOnParcel
        override val queryParamLabel: String = "triceps"

        @IgnoredOnParcel
        override val label: String = "Triceps"
    }

    companion object {

        fun getItems(): List<ExerciseDetails> {
            return listOf(
                Abdominals(),
                Abductors(),
                Adductors(),
                Biceps(),
                Calves(),
                Chest(),
                Forearms(),
                Glutes(),
                Hamstrings(),
                Lats(),
                LowerBack(),
                MiddleBack(),
                Neck(),
                Quadriceps(),
                Traps(),
                Triceps()
            )
        }

    }
}

