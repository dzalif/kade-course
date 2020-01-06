package com.kucingselfie.kadesubmission.ui.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import javax.inject.Inject

class DetailLeagueViewModel @Inject constructor(repo: LeagueRepository) : ViewModel() {
    private val _idLeague = MutableLiveData<String>()
    val idLeague: LiveData<String> get() = _idLeague

    fun setIdLeague(id: String) {
        _idLeague.value = id
    }

    val detailLeague: LiveData<Result<List<League>>> = Transformations.switchMap(_idLeague) {
        repo.getDetailLeague(it)
    }
}