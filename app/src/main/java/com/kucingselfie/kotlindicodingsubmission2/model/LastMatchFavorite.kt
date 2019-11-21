package com.kucingselfie.kotlindicodingsubmission2.model

data class LastMatchFavorite(
    val id: Long?,
    val matchId: String?,
    val matchName: String?,
    val matchPicture: String?,
    val matchTime: String?
) {
    companion object {
        const val TABLE_LAST_MATCH_FAVORITE: String = "TABLE_LAST_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_NAME: String = "MATCH_NAME"
        const val MATCH_PICTURE: String = "MATCH_PICTURE"
        const val MATCH_TIME: String = "MATCH_TIME"
    }
}