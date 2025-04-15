package com.example.getmefit.view.composables

import android.os.Parcelable
import com.example.getmefit.network.data.Exercise
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SetRepCount(
    val setCount: Int = 0,
    val repCount: Int = 0,
    val exercise: Exercise
) : Parcelable {

    companion object {

        fun List<SetRepCount>.updateCount(
            exercise: Exercise,
            setLogic: ((Int) -> Int)? = null,
            repLogic: ((Int) -> Int)? = null,
        ): List<SetRepCount> {
            return this.map {
                if (it.exercise == exercise) {
                    it.copy(
                        setCount = setLogic?.invoke(it.setCount) ?: it.setCount,
                        repCount = repLogic?.invoke(it.repCount) ?: it.repCount
                    )
                } else {
                    it
                }
            }
        }

        fun List<SetRepCount>.updateCount(
            exercise: Exercise,
            setCount: Int? = null,
            repCount: Int? = null,
        ): List<SetRepCount> {
            return this.map {
                if (it.exercise == exercise) {
                    it.copy(
                        setCount = setCount ?: it.setCount,
                        repCount = repCount ?: it.repCount
                    )
                } else {
                    it
                }
            }
        }

        fun List<SetRepCount>.checkErrorState(): Boolean {
            return !this.any { it.setCount == 0 || it.repCount == 0 }
        }

    }

}