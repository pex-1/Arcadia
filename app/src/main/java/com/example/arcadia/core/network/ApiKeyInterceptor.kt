package com.example.arcadia.core.network

import com.example.arcadia.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl = request.url.newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}