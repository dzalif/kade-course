package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.Match

class PreviousMatchResponse(
    @SerializedName("events")
    val events: List<Match>
)