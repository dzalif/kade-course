package com.kucingselfie.kotlindicodingsubmission2.model

import com.google.gson.annotations.SerializedName

class Match(
    @SerializedName("idEvent")
    val id: String,
    @SerializedName("strEvent")
    val event: String,
    @SerializedName("strThumb")
    val eventImage: String?
)