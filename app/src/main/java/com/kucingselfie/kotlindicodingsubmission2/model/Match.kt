package com.kucingselfie.kotlindicodingsubmission2.model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("idEvent")
    val id: String,
    @SerializedName("strEvent")
    val event: String,
    @SerializedName("strThumb")
    val eventImage: String?
)

data class NextMatch(
    @SerializedName("idEvent")
    val id: String? = null,
    @SerializedName("strEvent")
    val event: String? = null,
    @SerializedName("strThumb")
    val eventImage: String? = null
)

data class LastMatch(
    @SerializedName("idEvent")
    val id: String? = null,
    @SerializedName("strEvent")
    val event: String? = null,
    @SerializedName("strThumb")
    val eventImage: String? = null
)