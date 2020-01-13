package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.Standing

data class StandingResponse(
    @SerializedName("table")
    val data: List<Standing>
)