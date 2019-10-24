package com.kucingselfie.kotlindicodingsubmission2.model


import com.google.gson.annotations.SerializedName

data class DetailMatch(
    @SerializedName("dateEvent")
    val dateEvent: String,
    @SerializedName("idEvent")
    val idEvent: String,
    @SerializedName("intAwayScore")
    val intAwayScore: String?,
    @SerializedName("intHomeScore")
    val intHomeScore: String?,
    @SerializedName("strAwayTeam")
    val strAwayTeam: String,
    @SerializedName("strHomeTeam")
    val strHomeTeam: String,
    @SerializedName("strTime")
    val strTime: String?,
    @SerializedName("strHomeFormation")
    val strHomeFormation: String?,
    @SerializedName("strAwayFormation")
    val strAwayFormation: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeGK: String,
    @SerializedName("strHomeLineupForward")
    val strHomeFW: String,
    @SerializedName("strHomeLineupMidfield")
    val strHomeMD: String,
    @SerializedName("strHomeLineupDefense")
    val strHomeDF: String,
    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayGK: String,
    @SerializedName("strAwayLineupForward")
    val strAwayFW: String,
    @SerializedName("strAwayLineupMidfield")
    val strAwayMD: String,
    @SerializedName("strAwayLineupDefense")
    val strAwayDF: String
)