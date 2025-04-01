package com.example.getmefit.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.getmefit.network.data.Exercise

@Composable
fun ExerciseDetailsScreen(
    data: List<Exercise>
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(text = data.toString())
    }
}