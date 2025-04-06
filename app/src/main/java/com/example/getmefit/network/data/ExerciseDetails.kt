package com.example.getmefit.network.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
sealed interface ExerciseDetails : Parcelable {
    val queryParamLabel: String
    val label: String
    var isSelected: Boolean
    fun toggleSelection() {
        isSelected = !isSelected
    }

    companion object {
        fun List<ExerciseDetails>.updateSelection(selection: ExerciseDetails?): List<ExerciseDetails> {
            return this.map { item ->
                if (item == selection) {
                    item.apply { toggleSelection() }
                } else {
                    item
                }
            }
        }

        fun ExerciseDetails.updateSelection(isSelected: Boolean) : ExerciseDetails? {
            return if(isSelected) {
                this
            } else {
                null
            }
        }
    }
}