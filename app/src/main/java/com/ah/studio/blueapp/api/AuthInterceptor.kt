package com.ah.studio.blueapp.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
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
            .addHeader("date", "Wed, 08 Feb 2023 08:10:48 GMT")
            .addHeader("server", "server")
            .addHeader("platform", "hostinger")
            .addHeader("content-security-policy", "upgrade-insecure-requests")
            .build()

        return chain.proceed(request)
    }

}