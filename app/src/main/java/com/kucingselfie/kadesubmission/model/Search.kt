package com.kucingselfie.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("idEvent")
    val idEvent: String,
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("strEvent")
    val strEvent: String,
    @SerializedName("strLeague")
    val league: String,
    @SerializedName("dateEvent")
    val dateEvent: String,
    @SerializedName("strHomeTeam")
    val homeTeam: String,
    @SerializedName("strAwayTeam")
    val awayteam: String,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,
    @SerializedName("strThumb")
    val imageEvent: String?
)