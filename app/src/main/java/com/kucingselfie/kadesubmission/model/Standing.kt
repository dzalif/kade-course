package com.kucingselfie.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("name")
    val name: String,
    @SerializedName("teamid")
    val teamId: String,
    @SerializedName("played")
    val played: Int,
    @SerializedName("win")
    val win: Int,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("loss")
    val loss: Int,
    @SerializedName("total")
    val total: Int
)