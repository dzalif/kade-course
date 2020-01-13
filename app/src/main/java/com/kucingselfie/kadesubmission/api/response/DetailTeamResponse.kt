package com.kucingselfie.kadesubmission.api.response

import com.google.gson.annotations.SerializedName

data class DetailTeamResponse(
    val teams: List<DetailTeam>
)

data class DetailTeam(
    @SerializedName("idTeam")
    val teamId: String,
    @SerializedName("strTeam")
    val team: String,
    @SerializedName("strTeamBadge")
    val strTeamBadge: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("strStadium")
    val stadium: String
)