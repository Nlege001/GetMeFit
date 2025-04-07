package com.example.getmefit.view.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getmefit.R
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.view.data.ExerciseOptions

@Composable
fun ExerciseDetailsScreen(
    data: List<Exercise>,
    version: ExerciseOptions,
    onClick: (Exercise) -> Unit,
    createWorkout: (List<Exercise>) -> Unit
) {
    if (data.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.no_data),
                tint = Color(0xFFD0BCFF),
                contentDescription = ""
            )

            Text(
                text = "Can't find items for filter. Please try different filters",
                textAlign = TextAlign.Center
            )
        }

    } else {
        val savedExercises = rememberSaveable { mutableStateOf<List<Exercise>>(emptyList()) }
        Scaffold(
            topBar = {
                if (version.hasAddRemoveOption) {
                    AddedExerciseCount(savedExercises.value.size)
                }
            },
            content = { padding ->
                Column(
                    modifier = Modifier.padding(padding)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(data) { index, item ->
                            ExerciseCard(
                                exercise = item,
                                onClick = onClick,
                                version = version,
                                onRemove = {
                                    savedExercises.value = savedExercises.value.minus(it)
                                },
                                onAdd = {
                                    savedExercises.value = savedExercises.value.plus(it)
                                },
                                isAdded = { savedExercises.value.contains(it) },
                            )

                            if (index != data.lastIndex) {
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                    }
                }
            },
            floatingActionButton = {
                AnimatedVisibility(savedExercises.value.isNotEmpty()) {
                    Button(
                        onClick = {
                            if (savedExercises.value.isNotEmpty()) {
                                createWorkout(savedExercises.value)
                            }
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Create Workout",
                            fontSize = 16.sp
                        )
                    }
                }
            },
        )

    }
}

@Composable
private fun AddedExerciseCount(
    count: Int
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Added Exercises"
            )
            Text(
                text = count.toString()
            )
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}
