package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.DetailLeague

data class DetailLeagueResponse(
    @SerializedName("leagues")
    val leagues: List<DetailLeague>
)