package com.dsckiet.petapp.view.model.body


import com.squareup.moshi.Json

data class CreateEventBody(
    @Json(name = "date_time")
    var dateTime: String? = null,
    @Json(name = "event_description")
    var eventDescription: String? = null,
    @Json(name = "event_name")
    var eventName: String? = null,
    @Json(name = "event_type")
    var eventType: Int? = null,
    @Json(name = "event_venue")
    var eventVenue: String? = null
)