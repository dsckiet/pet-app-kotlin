package com.dsckiet.petapp.view.model.post


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    @Json(name = "msg")
    var msg: String? = null,
    @Json(name = "pet")
    var pet: Int? = null,
    @Json(name = "user")
    var user: List<User?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class User(
        @Json(name = "email")
        var email: String? = null,
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "username")
        var username: String? = null
    )
}