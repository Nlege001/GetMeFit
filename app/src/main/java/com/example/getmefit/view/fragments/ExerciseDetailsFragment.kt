package com.example.getmefit.view.fragments

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.getmefit.R
import com.example.getmefit.common.ViewStateCoordinator
import com.example.getmefit.view.composables.ExerciseDetailsScreen
import com.example.getmefit.view.viewmodels.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExerciseDetailsFragment : Fragment(R.layout.fragment_base) {

    @Inject
    lateinit var exerciseViewModelFactory: ExerciseViewModel.ExerciseViewModelFactory

    val args by navArgs<ExerciseDetailsFragmentArgs>()
    val exerciseViewModel: ExerciseViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
                    return exerciseViewModelFactory.create(args.navData) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentId = view.findViewById<ComposeView>(R.id.content)
        contentId.setContent {
            ViewStateCoordinator(
                viewModel = exerciseViewModel,
                stateProvider = { exerciseViewModel.exercises },
                onRefresh = {
                    exerciseViewModel.getExercises(
                        name = null,
                        type = args.navData.type?.queryParamLabel,
                        muscle = args.navData.muscle?.queryParamLabel,
                        difficulty = args.navData.difficulty?.queryParamLabel,
                    )
                }
            ) {
                ExerciseDetailsScreen(
                    data = it,
                    onClick = {}
                )
            }
        }
    }
}