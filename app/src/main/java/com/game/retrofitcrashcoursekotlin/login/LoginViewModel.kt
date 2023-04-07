package com.game.retrofitcrashcoursekotlin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.retrofitcrashcoursekotlin.models.UserRequest
import com.game.retrofitcrashcoursekotlin.models.UserResponse
import com.game.retrofitcrashcoursekotlin.repository.UserRepository
import com.game.retrofitcrashcoursekotlin.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


        val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
    get() = userRepository.userResponseLiveData


    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }

}