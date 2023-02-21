package com.ah.studio.blueapp

import android.content.Context
import com.ah.studio.blueapp.util.ApiConstants.FCM_TOKEN

class SessionManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("SessionManager", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", FCM_TOKEN)
    }

}