package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Standing

interface MatchDataSource {
    fun getNextMatch(id: String) : LiveData<Result<List<Match>>>
    fun getPreviousMatch(id: String) : LiveData<Result<List<Match>>>
    fun getDetailMatch(id: String) : LiveData<Result<List<DetailMatch>>>
    fun getDetailHomeTeam(id: String) : LiveData<Result<List<DetailTeam>>>
    fun getDetailAwayTeam(id: String) : LiveData<Result<List<DetailTeam>>>
    fun getStandings(id: String) : LiveData<Result<List<Standing>>>
}