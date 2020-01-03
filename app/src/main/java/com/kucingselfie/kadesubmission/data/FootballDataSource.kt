package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search

interface FootballDataSource {
    fun getListLeagues(): LiveData<Result<List<League>>>
    fun search(query: String): LiveData<Result<List<Search>>>
    fun getDetailLeague(id: String): LiveData<Result<List<League>>>
}