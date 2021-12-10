package com.dsckiet.petapp.view.model.get


import com.squareup.moshi.Json

class PetCategory : ArrayList<PetCategory.PetCategoryItem>() {
    data class PetCategoryItem(
        @Json(name = "category")
        var category: String? = null,
        @Json(name = "id")
        var id: Int? = null
    )
}