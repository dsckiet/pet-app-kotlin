package com.dsckiet.petapp.view.model.post


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedImageUpload(
    @Json(name = "curr_feed_id")
    var currFeedId: Int? = null,
    @Json(name = "msg")
    var msg: String? = null
)