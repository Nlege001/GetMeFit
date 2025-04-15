package com.example.getmefit.view.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmefit.R
import com.example.getmefit.common.formatDateLegacy
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.view.composables.SetRepCount.Companion.checkErrorState
import com.example.getmefit.view.composables.SetRepCount.Companion.updateCount
import com.example.getmefit.view.data.mockdata.mockExercise
import com.example.getmefit.view.viewmodels.CreateWorkoutViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.seconds

@Composable
fun CreateWorkoutScreen(
    viewModel: CreateWorkoutViewModel = hiltViewModel(),
    exercises: List<Exercise>,
    done: () -> Unit,
) {
    val workout =
        rememberSaveable { mutableStateOf<List<SetRepCount>>(exercises.map { SetRepCount(exercise = it) }) }
    val dateSelected = rememberSaveable { mutableStateOf<Long?>(null) }
    val isDateModalVisible = rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.mipmap.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            modifier = Modifier.padding(16.dp),
            text = "Please provide the number of sets and reps you did for each exercise. Swipe left to delete exercises; scroll to fill in data and save",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        val date = dateSelected.value
        if (date != null) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
                text = "Recording workout for ${formatDateLegacy(date)} "
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            onClick = {
                isDateModalVisible.value = true
            }
        ) {
            Text(
                text = if (date == null) {
                    "Select a date"
                } else {
                    "Change date"
                }
            )
        }

        if (isDateModalVisible.value) {
            DatePickerModal(
                onDateSelected = { dateSelected.value = it },
                onDismiss = { isDateModalVisible.value = false }
            )
        }

        if (workout.value.isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(top = 48.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Go back and choose exercises to add"
            )
        } else {
            LazyColumn {
                itemsIndexed(
                    workout.value,
                    key = { index, item -> item.hashCode() }
                ) { index, item ->
                    SwipeToDismissItem(
                        item = item,
                        onRemove = { workout.value = workout.value.minus(it) },
                        content = {
                            ExerciseWorkoutDetails(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                exercise = item.exercise,
                                setCount = item.setCount,
                                repCount = item.repCount,
                                onAddSet = { count, exercise ->
                                    workout.value = workout.value.updateCount(
                                        exercise = exercise,
                                        setLogic = { it -> it + 1 },
                                    )
                                },
                                onRemoveSet = { count, exercise ->
                                    workout.value = workout.value.updateCount(
                                        exercise = exercise,
                                        setLogic = { it -> it - 1 },
                                    )
                                },
                                onAddRep = { count, exercise ->
                                    workout.value = workout.value.updateCount(
                                        exercise = exercise,
                                        repLogic = { it -> it + 1 },
                                    )
                                },
                                onRemoveRep = { count, exercise ->
                                    workout.value = workout.value.updateCount(
                                        exercise = exercise,
                                        repLogic = { it -> it - 1 },
                                    )
                                },
                                onSetValueChange = { count, exercise ->
                                    workout.value = workout.value.updateCount(
                                        exercise,
                                        setCount = count
                                    )
                                },
                                onRepValueChange = { count, exercise ->
                                    workout.value = workout.value.updateCount(
                                        exercise,
                                        repCount = count
                                    )
                                }
                            )
                        }
                    )
                }

                item {
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClick = {
                            dateSelected.value?.let {
                                viewModel.saveWorkout(
                                    it,
                                    workout.value
                                )
                            }
                            done()
                        },
                        enabled = dateSelected.value != null && workout.value.checkErrorState()
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
private fun ExerciseWorkoutDetails(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    setCount: Int,
    repCount: Int,
    onAddSet: (Int, Exercise) -> Unit,
    onRemoveSet: (Int, Exercise) -> Unit,
    onAddRep: (Int, Exercise) -> Unit,
    onRemoveRep: (Int, Exercise) -> Unit,
    onSetValueChange: (Int, Exercise) -> Unit,
    onRepValueChange: (Int, Exercise) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exercise.name ?: "N/a",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextFieldWithAddRemoveCta(
                    label = "Sets",
                    count = setCount,
                    onAdd = { onAddSet(it, exercise) },
                    onRemove = { onRemoveSet(it, exercise) },
                    onValueChange = { onSetValueChange(it.toInt(), exercise) }
                )

                TextFieldWithAddRemoveCta(
                    label = "Reps",
                    count = repCount,
                    onAdd = { onAddRep(it, exercise) },
                    onRemove = { onRemoveRep(it, exercise) },
                    onValueChange = { onRepValueChange(it.toInt(), exercise) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun SwipeToDismissItem(
    item: SetRepCount,
    content: @Composable () -> Unit,
    onRemove: (SetRepCount) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                scope.launch {
                    delay(1.seconds)
                    onRemove(item)
                }
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissState,
        backgroundContent = {
            val bgColor by animateColorAsState(
                targetValue = when (swipeToDismissState.currentValue) {
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                    SwipeToDismissBoxValue.StartToEnd -> Color.Green
                    SwipeToDismissBoxValue.Settled -> Color.White
                },
                label = ""
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .background(bgColor)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Removed"
                )
            }
        },
    ) { content.invoke() }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewCreateWorkoutScreen() {
    CreateWorkoutScreen(
        exercises = listOf(
            mockExercise,
            mockExercise.copy(name = "name1"),
            mockExercise.copy(name = "name2"),
        ),
        done = { }
    )
}