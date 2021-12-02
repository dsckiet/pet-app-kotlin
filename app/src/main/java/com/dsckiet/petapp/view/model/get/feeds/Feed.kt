package com.dsckiet.petapp.view.model.get.feeds


import com.squareup.moshi.Json

data class Feed(
    @Json(name = "feed_caption")
    var feedCaption: String?,
    @Json(name = "feed_creation")
    var feedCreation: String?,
    @Json(name = "feed_img")
    var feedImg: String?,
    @Json(name = "feed_status")
    var feedStatus: String?,
    @Json(name = "feed_title")
    var feedTitle: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "user_id")
    var userId: Int?
)