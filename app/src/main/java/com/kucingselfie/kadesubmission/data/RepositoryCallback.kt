package com.kucingselfie.kadesubmission.data

import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Search

interface LoadListLeagueCallback {
    fun onSuccess(response: List<League>)
    fun onError(message: String)
}

interface SearchMatchCallback {
    fun onSuccess(response: List<Search>)
    fun onError(message: String)
}

interface LoadDetailLeagueCallback {
    fun onSuccess(response: List<League>)
    fun onError(message: String)
}

interface LoadNextMatchCallback {
    fun onSuccess(response: List<Match>)
    fun onError(message: String)
}

interface LoadPreviousMatchCallback {
    fun onSuccess(response: List<Match>)
    fun onError(message: String)
}

interface LoadDetailMatchCallback {
    fun onSuccess(response: List<DetailMatch>)
    fun onError(message: String)
}

interface LoadDetailTeamCallback {
    fun onSuccess(response: List<DetailTeam>)
    fun onError(message: String)
}