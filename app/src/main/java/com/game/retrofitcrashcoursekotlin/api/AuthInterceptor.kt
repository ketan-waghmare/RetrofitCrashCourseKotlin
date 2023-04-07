package com.game.retrofitcrashcoursekotlin.api

import com.game.retrofitcrashcoursekotlin.utils.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var preferenceManager : PreferenceManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = preferenceManager.getToken()
        request.addHeader("Authorization","$token")
        return chain.proceed(request.build())
    }
}