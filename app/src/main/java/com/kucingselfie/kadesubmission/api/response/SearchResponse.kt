package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.Search

data class SearchResponse(
    @SerializedName("event")
    val event: MutableList<Search>
)