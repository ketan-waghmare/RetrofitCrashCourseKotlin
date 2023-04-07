package com.game.retrofitcrashcoursekotlin.models

data class LocationDetailsResponse(
    val adminfees: List<Any>,
    val locationid: String,
    val locationspecificdetails: Locationspecificdetails,
    val monthly: Monthly,
    val parkingfees: List<Parkingfee>,
    val relatedlocations: List<String>,
    val violationfees: List<Violationfee>
)