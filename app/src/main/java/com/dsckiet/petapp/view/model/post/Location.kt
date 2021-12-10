package com.dsckiet.petapp.view.model.post


import com.squareup.moshi.Json

data class Location(
    @Json(name = "lat")
    var lat: String? = null,
    @Json(name = "long")
    var long: String? = null
)