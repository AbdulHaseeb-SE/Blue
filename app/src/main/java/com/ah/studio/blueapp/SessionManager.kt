package com.ah.studio.blueapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ah.studio.blueapp.util.ApiConstants.FCM_TOKEN

class SessionManager(context: Context) {
    val Context.userSessionDataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

    private val sharedPreferences = context.getSharedPreferences("SessionManager", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", FCM_TOKEN)
    }

}