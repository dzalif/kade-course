package com.kucingselfie.kadesubmission.ui.match.chooseleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.TheSportsApi
import com.kucingselfie.kadesubmission.common.ENGLAND
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.common.SOCCER
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ChooseLeagueViewModel @Inject constructor(repo: LeagueRepository) : ViewModel() {
    val leagues: LiveData<Result<List<League>>> = repo.getListLeagues()
}