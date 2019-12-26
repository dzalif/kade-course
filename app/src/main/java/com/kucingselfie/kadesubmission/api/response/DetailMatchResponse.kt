package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.DetailMatch

data class DetailMatchResponse(
    @SerializedName("events")
    val events: List<DetailMatch>
)