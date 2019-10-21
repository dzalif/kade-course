package com.kucingselfie.kotlindicodingsubmission2.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("idEvent")
    val idEvent: String,
    @SerializedName("strEvent")
    val strEvent: String,
    @SerializedName("dateEvent")
    val dateEvent: String
)