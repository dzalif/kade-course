package com.kucingselfie.kotlindicodingsubmission2.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kotlindicodingsubmission2.model.LastMatch
import com.kucingselfie.kotlindicodingsubmission2.model.Match

class PreviousMatchResponse(
    @SerializedName("events")
    val events: List<Match>
)