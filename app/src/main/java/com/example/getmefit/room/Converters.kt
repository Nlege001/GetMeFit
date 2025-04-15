package com.example.getmefit.room

import androidx.room.TypeConverter
import com.example.getmefit.view.composables.SetRepCount
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java, SetRepCount::class.java)
    private val adapter = moshi.adapter<List<SetRepCount>>(type)

    @TypeConverter
    fun fromSetRepList(value: List<SetRepCount>): String {
        return adapter.toJson(value)
    }

    @TypeConverter
    fun toSetRepList(value: String): List<SetRepCount> {
        return adapter.fromJson(value) ?: emptyList()
    }
}