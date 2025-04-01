package com.example.getmefit.network

import com.example.getmefit.BuildConfig
import okhttp3.Interceptor
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "https://api.api-ninjas.com/"

    // Create an Interceptor to add the API key to headers
    private val apiKeyInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", BuildConfig.API_KEY) // Use BuildConfig.API_KEY to access your API key
            .build()
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor) // Add the interceptor to the client
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // Use the client with the interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}