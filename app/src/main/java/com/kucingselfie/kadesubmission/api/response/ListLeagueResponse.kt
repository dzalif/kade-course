package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.League

data class ListLeagueResponse(
    @SerializedName("countrys")
    val countries: List<League>
)