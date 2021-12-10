package com.dsckiet.petapp.view.model.get


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllEventTypes(
    @Json(name = "all_event_types")
    var allEventTypes: List<AllEventType?>? = null,
    @Json(name = "msg")
    var msg: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class AllEventType(
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "type_decription")
        var typeDecription: String? = null,
        @Json(name = "type_name")
        var typeName: String? = null
    )
}