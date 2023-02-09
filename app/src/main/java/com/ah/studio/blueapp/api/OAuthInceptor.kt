package com.ah.studio.blueapp.api

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(private val tokenType: String, private val accessToken: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request
            .newBuilder()
            .header("Authorization", "$tokenType $accessToken")
            .addHeader("Accept", "application/json")
            .addHeader("Connection", "Keep-Alive")
            .addHeader("Keep-Alive", "timeout=5, max=100")
            .addHeader("x-powered-by", "PHP/8.1.13")
            .addHeader("cache-control", "no-cache, private")
            .addHeader("content-type", "application/json")
            .addHeader("x-ratelimit-limit", "60")
            .addHeader("x-ratelimit-remaining", "58")
            .addHeader("access-control-allow-origin", "*")
            .addHeader("content-length", "213")
            .addHeader("content-encoding", "br")
            .addHeader("vary", "Accept-Encoding")
            .addHeader("server", "server")
            .addHeader("platform", "hostinger")
            .addHeader("content-security-policy", "upgrade-insecure-requests")
            .build()

        return chain.proceed(request)
    }
}