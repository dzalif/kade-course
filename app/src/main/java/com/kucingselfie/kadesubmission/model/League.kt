package com.kucingselfie.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    val id: String,
    @SerializedName("strLeague")
    val leagueName: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("strLogo")
    val logo: String?,
    @SerializedName("strBadge")
    val badge: String?,
    @SerializedName("strNaming")
    val strNaming: String
)