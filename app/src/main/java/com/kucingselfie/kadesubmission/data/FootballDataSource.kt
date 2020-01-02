package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.model.League

interface FootballDataSource {
    fun getListLeagues(): LiveData<Result<List<League>>>
}