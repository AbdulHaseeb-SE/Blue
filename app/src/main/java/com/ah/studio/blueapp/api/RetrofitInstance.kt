package com.ah.studio.blueapp.api

import com.ah.studio.blueapp.util.ApiConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   private val client  = OkHttpClient().apply {
        this.newBuilder().addInterceptor(AuthInterceptor()).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}