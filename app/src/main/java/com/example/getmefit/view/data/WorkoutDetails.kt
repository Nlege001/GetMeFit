package com.example.getmefit.view.data

import android.os.Parcelable
import com.example.getmefit.view.composables.SetRepCount
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutDetails(
    val data: List<SetRepCount>,
    val date: Long
): Parcelable