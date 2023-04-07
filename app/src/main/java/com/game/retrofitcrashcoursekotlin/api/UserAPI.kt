package com.game.retrofitcrashcoursekotlin.api

import com.game.retrofitcrashcoursekotlin.models.LocationDetailsResponse
import com.game.retrofitcrashcoursekotlin.models.UserRequest
import com.game.retrofitcrashcoursekotlin.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {

    @POST("login")
    suspend fun signin(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("logout")
    suspend fun logout()

}