package com.kucingselfie.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    val id: String,
    @SerializedName("strTeam")
    val teamName: String,
    @SerializedName("strTeamBadge")
    val badge: String
)