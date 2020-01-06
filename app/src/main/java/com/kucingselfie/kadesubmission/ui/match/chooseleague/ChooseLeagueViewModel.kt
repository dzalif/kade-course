package com.kucingselfie.kadesubmission.ui.match.chooseleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import javax.inject.Inject

class ChooseLeagueViewModel @Inject constructor(repo: LeagueRepository) : ViewModel() {
    val leagues: LiveData<Result<List<League>>> = repo.getListLeagues()
}