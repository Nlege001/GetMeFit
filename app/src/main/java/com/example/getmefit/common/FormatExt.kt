package com.example.getmefit.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateLegacy(millis: Long): String {
    val date = Date(millis)
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
    return formatter.format(date)
}