package com.example.getmefit.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.getmefit.network.data.Difficulty
import com.example.getmefit.network.data.ExerciseDetails
import com.example.getmefit.network.data.ExerciseDetails.Companion.updateSelection
import com.example.getmefit.network.data.ExerciseType
import com.example.getmefit.network.data.Muscle

@Composable
fun ExerciseScreen(
    onDoneClick: (
        ExerciseDetails?,
        ExerciseDetails?,
        ExerciseDetails?
    ) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.weight(1f))
        val selectedMuscle = rememberSaveable { mutableStateOf<ExerciseDetails?>(null) }
        val muscles = remember {
            derivedStateOf { Muscle.getItems().updateSelection(selectedMuscle.value) }
        }
        FlowRows(
            label = "Muscles",
            items = muscles.value
        ) { selectedMuscle.value = it }

        val selectedType = rememberSaveable { mutableStateOf<ExerciseDetails?>(null) }
        val types = remember {
            derivedStateOf { ExerciseType.getItems().updateSelection(selectedType.value) }
        }
        FlowRows(label = "Types", items = types.value) { selectedType.value = it }


        val selectedDifficulty = rememberSaveable { mutableStateOf<ExerciseDetails?>(null) }
        val difficulty = remember {
            derivedStateOf { Difficulty.getItems().updateSelection(selectedDifficulty.value) }
        }
        FlowRows(label = "Difficulty", items = difficulty.value) { selectedDifficulty.value = it }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            onClick = {
                onDoneClick(
                    selectedMuscle.value,
                    selectedDifficulty.value,
                    selectedType.value
                )
            },
            enabled = selectedMuscle.value != null ||
                    selectedDifficulty.value != null ||
                    selectedType.value != null
        ) {
            Text("Look up")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRows(
    label: String,
    items: List<ExerciseDetails>,
    onClick: (ExerciseDetails) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = label
        )

        FlowRow {
            items.forEach { Chip(it) { onClick(it) } }
        }
    }

}

@Composable
private fun Chip(
    detail: ExerciseDetails,
    onClick: (ExerciseDetails) -> Unit,
) {
    FilterChip(
        selected = detail.isSelected,
        onClick = { onClick(detail) },
        label = {
            Text(text = detail.label)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExerciseScreen() {
    ExerciseScreen { _, _, _ -> }
}