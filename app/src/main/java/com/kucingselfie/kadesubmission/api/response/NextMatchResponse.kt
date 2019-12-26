package com.kucingselfie.kotlindicodingsubmission2.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kotlindicodingsubmission2.model.Match
import com.kucingselfie.kotlindicodingsubmission2.model.NextMatch

class NextMatchResponse(
    @SerializedName("events")
    val events: List<Match>
)