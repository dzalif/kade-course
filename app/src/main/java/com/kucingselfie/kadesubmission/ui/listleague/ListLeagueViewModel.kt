package com.kucingselfie.kadesubmission.ui.listleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import javax.inject.Inject

class ListLeagueViewModel @Inject constructor(repository: LeagueRepository) : ViewModel() {
    val leagues: LiveData<Result<List<League>>> = repository.getListLeagues()
}