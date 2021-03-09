package com.mrz.apiwork

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor{
    //Введешь токен
    val token = 12345
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }

}