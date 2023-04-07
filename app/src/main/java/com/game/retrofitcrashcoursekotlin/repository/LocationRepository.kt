package com.game.retrofitcrashcoursekotlin.repository

import android.util.Log
import retrofit2.Response
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.game.retrofitcrashcoursekotlin.api.LocationAPI
import com.game.retrofitcrashcoursekotlin.api.UserAPI
import com.game.retrofitcrashcoursekotlin.utils.NetworkResult
import com.game.retrofitcrashcoursekotlin.models.LocationDetailsResponse
import com.google.gson.Gson

class LocationRepository @Inject constructor(val locationAPI: LocationAPI) {

    private val _locationResponseLiveData = MutableLiveData<NetworkResult<LocationDetailsResponse>>()
    val locationResponseLiveData : LiveData<NetworkResult<LocationDetailsResponse>>
        get() = _locationResponseLiveData

    suspend fun getLocationDetails(locationId : String) {
        _locationResponseLiveData.postValue(NetworkResult.Loading())
        val response = locationAPI.getLocationDetails(locationId)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<LocationDetailsResponse>) {
        if(response.isSuccessful && response.body() != null) {
            Log.d("LocationRepository_log", " = ${response.body()}")
            _locationResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            _locationResponseLiveData.postValue(NetworkResult.Error("Error found"))
        } else {
            _locationResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}