package com.kucingselfie.kotlindicodingsubmission2.model

import com.google.gson.annotations.SerializedName

data class DetailLeague(
    @SerializedName("idLeague")
    val id: String,
    @SerializedName("strLeague")
    val leagueName: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("strLogo")
    val logo: String,
    @SerializedName("strBadge")
    val badge: String,
    @SerializedName("strNaming")
    val strNaming: String
)