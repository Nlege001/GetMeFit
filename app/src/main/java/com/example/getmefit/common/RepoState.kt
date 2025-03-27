package com.example.getmefit.common

sealed class RepoState<out T> {
    object Loading : RepoState<Nothing>()

    data class Error(
        val message: String? = null
    ) : RepoState<Nothing>()

    data class Content<out T>(
        val data: T
    ) : RepoState<T>()

}