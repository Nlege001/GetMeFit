package com.example.getmefit.view

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.getmefit.R
import com.example.getmefit.view.data.ExerciseDetailsNavData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseFragment : Fragment(R.layout.fragment_base) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.content)
        val navController = findNavController()
        composeView.setContent {
            ExerciseScreen { muscle, difficulty, type ->
                navController.navigate(
                    ExerciseDetailsFragmentDirections.navToExerciseDetailsFragment(
                        navData = ExerciseDetailsNavData(
                            muscle = muscle,
                            difficulty = difficulty,
                            type = type
                        )
                    )
                )
            }
        }
    }
}