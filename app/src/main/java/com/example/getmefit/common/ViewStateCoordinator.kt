package com.example.getmefit.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T, VM> ViewStateCoordinator(
    viewModel: VM,
    stateProvider: VM.() -> StateFlow<RepoState<T>>,
    onRefresh: () -> Unit,
    errorView: @Composable () -> Unit = { ErrorScreen { onRefresh } },
    loadingView: @Composable () -> Unit = { LoadingScreen() },
    contentView: @Composable (T) -> Unit
) {
    val state = viewModel.stateProvider().collectAsState(RepoState.Loading).value
    when (state) {
        RepoState.Loading -> loadingView.invoke()
        is RepoState.Content -> contentView.invoke(state.data)
        is RepoState.Error -> errorView.invoke()
    }
}