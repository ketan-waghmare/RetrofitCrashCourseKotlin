package com.game.retrofitcrashcoursekotlin.models

data class ValidPlate(
    val endDateUtc: String,
    val expirationDate: String,
    val expirationDateUtc: String,
    val licensePlate: String,
    val locationID: String,
    val source: String,
    val sourceCode: String,
    val startDateUtc: String
)