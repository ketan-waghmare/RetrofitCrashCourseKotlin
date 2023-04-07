package com.game.retrofitcrashcoursekotlin.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.retrofitcrashcoursekotlin.models.LocationDetailsResponse
import com.game.retrofitcrashcoursekotlin.repository.LocationRepository
import com.game.retrofitcrashcoursekotlin.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel  @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {

    val locationResponseLiveData : LiveData<NetworkResult<LocationDetailsResponse>>
        get() = locationRepository.locationResponseLiveData


    fun getLocationDetails(locationId : String) {
        viewModelScope.launch {
            locationRepository.getLocationDetails(locationId)
        }
    }

    fun logout() {
        viewModelScope.launch {

        }
    }
}