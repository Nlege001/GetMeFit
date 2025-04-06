package com.example.getmefit.network.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Exercise(
    val name: String?,
    val type: String?,
    val muscle: String?,
    val equipment: String?,
    val difficulty: String?,
    val instructions: String?
) : Parcelable