package com.example.getmefit.view

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.getmefit.common.ViewStateCoordinator
import com.example.stocktracker.R

class ExerciseFragment : Fragment(R.layout.fragment_exercise) {

    val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentId = view.findViewById<ComposeView>(R.id.content)
        contentId.setContent {
            ViewStateCoordinator(
                viewModel = exerciseViewModel,
                stateProvider = { exerciseViewModel.exercises },
                onRefresh = {}
            ) {
                // TODO
            }
        }
    }
}