package com.dsckiet.petapp.view.model.post


import com.squareup.moshi.Json


data class CreateEvent(
    @Json(name = "msg")
    var msg: String? = null
)