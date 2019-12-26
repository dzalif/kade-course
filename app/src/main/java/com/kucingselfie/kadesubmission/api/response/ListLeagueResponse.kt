package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.DetailLeague

data class ListLeagueResponse(
    @SerializedName("countrys")
    val countrys: List<DetailLeague>
)