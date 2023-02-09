package com.ah.studio.blueapp.api

import android.content.Context
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.util.ApiConstants.BASE_URL
import com.ah.studio.blueapp.util.ApiConstants.FCM_TOKEN
import com.ah.studio.blueapp.util.ApiConstants.TOKEN_TYPE
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance(context: Context) {
    private val oAuthClient = OkHttpClient().apply {
        this.newBuilder().addInterceptor(
            OAuthInterceptor(
                TOKEN_TYPE,
                SessionManager(context).getToken() ?: FCM_TOKEN
            )
        ).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(oAuthClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}