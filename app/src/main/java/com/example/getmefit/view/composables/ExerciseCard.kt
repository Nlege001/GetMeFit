package com.example.getmefit.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.getmefit.network.data.Exercise
import com.example.getmefit.view.data.ExerciseOptions

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    onClick: (Exercise) -> Unit,
    version: ExerciseOptions,
    onAdd: (Exercise) -> Unit,
    onRemove: (Exercise) -> Unit,
    isAdded: (Exercise?) -> Boolean,
) {
    val difficultyColor = when (exercise.difficulty?.lowercase()) {
        "beginner" -> Color(0xFF4CAF50)  // Green for easy
        "intermediate" -> Color(0xFFFFC107)  // Yellow for medium
        "advanced" -> Color(0xFFF44336)  // Red for hard
        else -> Color.Gray
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .clickable(true, onClick = { onClick(exercise) })
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                // Exercise Name
                Text(
                    text = exercise.name ?: "Unknown Exercise",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tags Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ExerciseTag(
                        text = exercise.type ?: "N/A",
                        color = MaterialTheme.colorScheme.primary
                    )
                    ExerciseTag(
                        text = exercise.muscle ?: "N/A",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    ExerciseTag(
                        text = exercise.equipment ?: "N/A",
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Difficulty Badge
                Box(
                    modifier = Modifier
                        .background(difficultyColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = exercise.difficulty?.uppercase() ?: "UNKNOWN",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Instructions
                Text(
                    text = exercise.instructions ?: "No instructions available.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (version.hasAddRemoveOption) {
                AddRemoveCtas(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onAdd = { onAdd(exercise) },
                    onRemove = { onRemove(exercise) },
                    isAdded = isAdded,
                    exercise = exercise
                )
            }
        }
    }
}

// Helper Composable for Tags
@Composable
fun ExerciseTag(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}