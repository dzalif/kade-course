package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.kadesubmission.model.Team

data class ListTeamResponse(
    @SerializedName("teams")
    val data: List<Team>
)