package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.Team

data class SearchTeamResponse(
    @SerializedName("teams")
    val data: List<Team>? = null
)