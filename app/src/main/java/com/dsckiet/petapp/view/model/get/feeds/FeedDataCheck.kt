package com.dsckiet.petapp.view.model.get.feeds


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedDataCheck(
    @Json(name = "feeds")
    var feeds: List<Feed>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Feed(
        @Json(name = "feed_caption")
        var feedCaption: String? = null,
        @Json(name = "feed_creation")
        var feedCreation: String? = null,
        @Json(name = "feed_img")
        var feedImg: String? = null,
        @Json(name = "feed_status")
        var feedStatus: String? = null,
        @Json(name = "feed_title")
        var feedTitle: String? = null,
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "user_id")
        var userId: Int? = null
    )
}