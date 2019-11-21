package com.kucingselfie.kotlindicodingsubmission2.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague

data class ListLeagueResponse(
    @SerializedName("countrys")
    val countrys: List<DetailLeague>
)