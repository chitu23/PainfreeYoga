package com.simats.painfreeyoga.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // 🔗 Your base URL (make sure it's active and updated!)
    const val BASE_URL = "https://x961gtm8-80.inc1.devtunnels.ms/painfreeyoga/"

    // 🪵 Logging interceptor (to log request & response)
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // ⏱ OkHttpClient with proper timeout settings
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)   // ⏱ Connection timeout
        .readTimeout(30, TimeUnit.SECONDS)      // ⏱ Read timeout
        .writeTimeout(30, TimeUnit.SECONDS)     // ⏱ Write timeout
        .build()

    // 🚀 Retrofit instance with Gson converter
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // 🧩 Attach the OkHttp client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
