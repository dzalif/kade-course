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
    val strTime: String?
)