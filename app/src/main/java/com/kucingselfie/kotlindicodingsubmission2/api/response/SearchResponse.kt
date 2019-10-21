package com.kucingselfie.kotlindicodingsubmission2.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kotlindicodingsubmission2.model.Search

data class SearchResponse(
    @SerializedName("event")
    val event: MutableList<Search>
)