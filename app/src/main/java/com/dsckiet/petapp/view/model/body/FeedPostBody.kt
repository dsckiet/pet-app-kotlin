package com.dsckiet.petapp.view.model.body


import com.squareup.moshi.Json


data class FeedPostBody(
    @Json(name = "curr_feed_id")
    var currFeedId: Int? = null,
    @Json(name = "feed_caption")
    var feedCaption: String? = null,
    @Json(name = "feed_title")
    var feedTitle: String? = null
)