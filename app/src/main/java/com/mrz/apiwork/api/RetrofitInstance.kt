package com.mrz.apiwork.api

import com.mrz.apiwork.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //не трожь и не забудь подключить интернет в манифесте
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}