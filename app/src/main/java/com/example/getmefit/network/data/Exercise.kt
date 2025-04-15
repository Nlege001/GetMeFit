package com.example.getmefit.network.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
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