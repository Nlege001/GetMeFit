package com.example.getmefit.view.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.getmefit.R
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.view.data.mockdata.mockExercise

@Composable
fun AddRemoveCtas(
    exercise: Exercise? = null,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    isRemoveEnabled: Boolean = true,
    isAdded: (Exercise?) -> Boolean = { false },
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(isAdded(exercise)) {
                Icon(
                    modifier = Modifier.padding(top = 8.dp),
                    painter = painterResource(R.drawable.baseline_check_circle_24),
                    contentDescription = null,
                    tint = Color.Green
                )
            }

            AnimatedVisibility(!isAdded(exercise)) {
                IconButton(
                    onClick = onAdd
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            }

            IconButton(
                onClick = onRemove,
                enabled = isRemoveEnabled
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddRemoveCtas_UnAdded() {
    AddRemoveCtas(
        onRemove = {},
        onAdd = {},
        isAdded = { false },
        exercise = mockExercise
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddRemoveCtas_Added() {
    AddRemoveCtas(
        onRemove = {},
        onAdd = {},
        isAdded = { true },
        exercise = mockExercise
    )
}