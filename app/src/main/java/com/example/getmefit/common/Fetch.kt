package com.example.getmefit.common

import retrofit2.Response

suspend fun <T> fetch(apiCall: suspend () -> Response<T>): RepoState<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                RepoState.Content(body)
            } else {
                RepoState.Error("Empty response body")
            }
        } else {
            RepoState.Error("HTTP Error: ${response.code()} - ${response.message()}")
        }
    } catch (e: Exception) {
        RepoState.Error("Error: ${e.localizedMessage}")
    }
}