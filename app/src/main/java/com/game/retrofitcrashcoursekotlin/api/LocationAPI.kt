package com.game.retrofitcrashcoursekotlin.api

import com.game.retrofitcrashcoursekotlin.models.LocationDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationAPI {

    @GET("v3/locationdetails/{locationId}/hash")
    suspend fun getLocationDetails(@Path("locationId") locationId: String) : Response<LocationDetailsResponse>


}