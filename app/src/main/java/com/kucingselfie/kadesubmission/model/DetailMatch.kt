package com.kucingselfie.kotlindicodingsubmission2.model


import com.google.gson.annotations.SerializedName

data class DetailMatch(
    @SerializedName("dateEvent")
    val dateEvent: String? = null,
    @SerializedName("idEvent")
    val idEvent: String? = null,
    @SerializedName("strEvent")
    val eventName: String? = null,
    @SerializedName("intAwayScore")
    val intAwayScore: String? = "0",
    @SerializedName("intHomeScore")
    val intHomeScore: String? = "0",
    @SerializedName("strAwayTeam")
    val strAwayTeam: String? = null,
    @SerializedName("strHomeTeam")
    val strHomeTeam: String? = null,
    @SerializedName("strTime")
    val strTime: String? = null,
    @SerializedName("strHomeFormation")
    val strHomeFormation: String? = null,
    @SerializedName("strAwayFormation")
    val strAwayFormation: String? = null,
    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeGK: String? = null,
    @SerializedName("strHomeLineupForward")
    val strHomeFW: String? = null,
    @SerializedName("strHomeLineupMidfield")
    val strHomeMD: String? = null,
    @SerializedName("strHomeLineupDefense")
    val strHomeDF: String? = null,
    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayGK: String? = null,
    @SerializedName("strAwayLineupForward")
    val strAwayFW: String? = null,
    @SerializedName("strAwayLineupMidfield")
    val strAwayMD: String? = null,
    @SerializedName("strAwayLineupDefense")
    val strAwayDF: String? = null
)