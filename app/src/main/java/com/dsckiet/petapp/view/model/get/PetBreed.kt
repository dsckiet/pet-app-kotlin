package com.dsckiet.petapp.view.model.get


import com.squareup.moshi.Json

class PetBreed : ArrayList<PetBreed.PetBreedItem>() {
    data class PetBreedItem(
        @Json(name = "breed")
        var breed: String? = null,
        @Json(name = "category_id")
        var categoryId: Int? = null,
        @Json(name = "id")
        var id: Int? = null
    )
}