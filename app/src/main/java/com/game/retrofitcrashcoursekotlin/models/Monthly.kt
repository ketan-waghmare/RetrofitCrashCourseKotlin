package com.game.retrofitcrashcoursekotlin.models

data class Monthly(
    val changed: Boolean,
    val details: Any,
    val digitalData: Any,
    val hash: String,
    val mpDetails: List<MpDetail>,
    val rmDetails: List<Any>,
    val subscriptions: Subscriptions,
    val validPlates: List<ValidPlate>
)