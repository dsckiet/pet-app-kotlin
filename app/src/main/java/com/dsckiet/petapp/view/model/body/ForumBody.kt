package com.dsckiet.petapp.view.model.body


import com.squareup.moshi.Json

data class ForumBody(
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "forumname")
    var forumname: String? = null,
    @Json(name = "users")
    var users: List<String?>? = null,
    @Json(name = "visibility")
    var visibility: String? = null
)