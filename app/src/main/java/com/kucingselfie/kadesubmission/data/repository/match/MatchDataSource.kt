package com.kucingselfie.kadesubmission.data.repository.match

import androidx.lifecycle.LiveData
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Standing
import com.kucingselfie.kadesubmission.model.Team

interface MatchDataSource {
    fun getNextMatch(id: String) : LiveData<Result<List<Match>>>
    fun getPreviousMatch(id: String) : LiveData<Result<List<Match>>>
    fun getDetailMatch(id: String) : LiveData<Result<List<DetailMatch>>>
    fun getDetailHomeTeam(id: String) : LiveData<Result<List<DetailTeam>>>
    fun getDetailAwayTeam(id: String) : LiveData<Result<List<DetailTeam>>>
    fun getStandings(id: String) : LiveData<Result<List<Standing>>>
    fun getListTeam(id: String) : LiveData<Result<List<Team>>>
    fun searchTeam(query: String) : LiveData<Result<List<Team>>>
}