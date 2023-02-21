package com.ah.studio.blueapp.api

import android.content.Context
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.util.ApiConstants.BASE_URL
import com.ah.studio.blueapp.util.ApiConstants.FCM_TOKEN
import com.ah.studio.blueapp.util.ApiConstants.TOKEN_TYPE
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class VolleyInstance(context: Context) {
    private val volleySingleton = VolleySingleton.getInstance(context)
    private val token = SessionManager(context).getToken()
        ?.ifEmpty { FCM_TOKEN } ?: FCM_TOKEN

    fun jsonObjectGetRequest(
        endPoint: String,
        jsonObject: JSONObject? = null,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            BASE_URL + endPoint,
            jsonObject,
            listener,
            errorListener
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Authorization"] = "$TOKEN_TYPE $token"
                return params
            }
        }
        jsonObjectRequest.setShouldCache(false)
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            200000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        volleySingleton.addToRequestQueue(jsonObjectRequest)
    }

    fun jsonObjectPostRequest(
        endPoint: String,
        jsonObject: JSONObject?,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST,
            BASE_URL + endPoint,
            jsonObject,
            listener,
            errorListener
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Authorization"] = "$TOKEN_TYPE $token"
                return params
            }
        }
        jsonObjectRequest.setShouldCache(false)
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            150000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        volleySingleton.addToRequestQueue(jsonObjectRequest)
    }

    fun deleteRequest(
        endPoint: String,
        jsonObject: JSONObject? = null,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.DELETE,
            BASE_URL + endPoint,
            jsonObject,
            listener,
            errorListener
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Authorization"] = "$TOKEN_TYPE $token"
                return params
            }
        }
        jsonObjectRequest.setShouldCache(false)
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            150000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        volleySingleton.addToRequestQueue(jsonObjectRequest)
    }
}