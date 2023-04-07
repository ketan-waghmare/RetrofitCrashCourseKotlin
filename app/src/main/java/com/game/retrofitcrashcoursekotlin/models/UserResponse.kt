package com.game.retrofitcrashcoursekotlin.models

data class UserResponse(
    val entity: String,
    val fullname: String,
    val locations: List<Location>,
    val token: String,
    val username: String
)