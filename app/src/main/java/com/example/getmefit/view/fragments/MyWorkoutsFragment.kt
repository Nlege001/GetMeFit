package com.example.getmefit.view.fragments

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.findNavController
import com.example.getmefit.R
import com.example.getmefit.common.formatDateLegacy
import com.example.getmefit.view.composables.SetRepCount
import com.example.getmefit.view.viewmodels.MyWorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyWorkoutsFragment : Fragment(R.layout.fragment_base) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = view.findViewById<ComposeView>(R.id.content)
        val navController = findNavController()
        content.setContent {
            MyWorkoutScreen()
        }
    }
}

@Composable
fun MyWorkoutScreen(
    myWorkoutViewModel: MyWorkoutViewModel = hiltViewModel()
) {
    val groupedWorkouts = myWorkoutViewModel.workouts.collectAsState(emptyMap()).value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "My workouts",
                style = MaterialTheme.typography.headlineLarge
            )

            HorizontalDivider()
        }

        groupedWorkouts.forEach { (date, workoutList) ->
            item {
                Text(
                    text = formatDateLegacy(date),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(workoutList) { workout ->
                WorkoutCard(
                    repSetData = workout,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun WorkoutCard(
    repSetData: SetRepCount,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SetRepItem(repSetData)
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun SetRepItem(setRep: SetRepCount) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = setRep.exercise.name ?: "Unnamed Exercise",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "${setRep.setCount} sets x ${setRep.repCount} reps",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = "${setRep.exercise.muscle ?: ""} • ${setRep.exercise.equipment ?: ""} • ${setRep.exercise.difficulty ?: ""}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}