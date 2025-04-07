package com.example.getmefit.view.fragments

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.getmefit.R
import com.example.getmefit.view.composables.ListItemWithChevron
import com.example.getmefit.view.data.ExerciseOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseOptionFragment : Fragment(R.layout.fragment_base) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = view.findViewById<ComposeView>(R.id.content)
        val navController = findNavController()
        content.setContent {
            ExerciseOptionScreen(
                onClick = {
                    navController.navigate(ExerciseFragmentDirections.navToExerciseFragment(it))
                }
            )
        }
    }
}

@Composable
fun ExerciseOptionScreen(
    onClick: (ExerciseOptions) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.mipmap.app_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(300.dp)
        )

        ExerciseOptions.entries.forEachIndexed { index, item ->
            ListItemWithChevron(
                label = item.label,
                subtitle = item.subtitle,
                isEnabled = item.isEnabled,
                showDivider = ExerciseOptions.entries.lastIndex != index,
                onClick = { onClick(item) }
            )
        }

    }
}