package com.ah.studio.blueapp.util

import android.content.Context
import com.ah.studio.blueapp.ui.screens.home.domain.dto.booking.Product
import com.google.gson.Gson

class BookingDetailsManager(context: Context) {
    private val bookingDetailsManager =
        context.getSharedPreferences("BookingDetailsManager", Context.MODE_PRIVATE)

    fun saveSingleDetails(keyName: String, value: String) {
        bookingDetailsManager.edit().putString(keyName, value).apply()
    }

    fun saveProductList(keyName: String, selectedProductList: MutableList<Product>) {
        bookingDetailsManager.edit().putString(keyName, Gson().toJson(selectedProductList)).apply()
    }

    fun retrieveDetails(keyName: String): String? {
        return bookingDetailsManager.getString(keyName, null)
    }
}