package com.kucingselfie.kotlindicodingsubmission2.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kotlindicodingsubmission2.model.DetailMatch
import com.kucingselfie.kotlindicodingsubmission2.model.Match

data class DetailMatchResponse(
    @SerializedName("events")
    val events: List<DetailMatch>
)