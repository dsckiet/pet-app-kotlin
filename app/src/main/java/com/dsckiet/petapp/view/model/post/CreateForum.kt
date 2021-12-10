package com.dsckiet.petapp.view.model.post


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateForum(
    @Json(name = "forum-id")
    var forumId: Int? = null,
    @Json(name = "msg")
    var msg: String? = null
)