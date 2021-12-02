package com.dsckiet.petapp.view.model.get.feeds


import com.squareup.moshi.Json

data class FeedsData(
    @Json(name = "feeds")
    var feeds: List<Feed>?
)