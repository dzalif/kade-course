package com.kucingselfie.kotlindicodingsubmission2.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague

class DetailLeagueResponse(
    @SerializedName("leagues")
    val leagues: List<DetailLeague>
)