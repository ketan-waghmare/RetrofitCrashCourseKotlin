package com.game.retrofitcrashcoursekotlin.models

data class Locationspecificdetails(
    val collectionscutoff: Int,
    val dailymaxrate: Int,
    val spaces: Int,
    val ticketfooter: String,
    val ticketheader: String,
    val tickettitle: String,
    val violationdiscount: Int,
    val violationdiscountdaysout: Int
)