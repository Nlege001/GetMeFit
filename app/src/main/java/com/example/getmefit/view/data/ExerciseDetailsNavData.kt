package com.example.getmefit.view.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.getmefit.network.data.ExerciseDetails
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ExerciseDetailsNavData(
    val muscle: ExerciseDetails?,
    val difficulty: ExerciseDetails?,
    val type: ExerciseDetails?
) : Parcelable