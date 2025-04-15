package com.example.getmefit.view.fragments

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.getmefit.R
import com.example.getmefit.view.composables.CreateWorkoutScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWorkoutFragment : Fragment(R.layout.fragment_base) {

    val args by navArgs<CreateWorkoutFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = view.findViewById<ComposeView>(R.id.content)
        val navController = findNavController()
        composeView.setContent {
            CreateWorkoutScreen(
                exercises = args.exercises.toList(),
                done = { navController.navigate(MyWorkoutsFragmentDirections.navToMyWorkoutsFragment()) }
            )
        }
    }

}