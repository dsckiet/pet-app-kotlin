package com.dsckiet.petapp.view.model.get.logout


import com.squareup.moshi.Json

data class Logout(
    @Json(name = "msg")
    var msg: String?
)