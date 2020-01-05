package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.model.Match

interface MatchDataSource {
    fun getNextMatch(id: String) : LiveData<Result<List<Match>>>
    fun getPreviousMatch(id: String) : LiveData<Result<List<Match>>>
}