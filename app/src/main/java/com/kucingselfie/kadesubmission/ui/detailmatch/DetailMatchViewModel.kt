package com.kucingselfie.kadesubmission.ui.detailmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.MatchRepository
import com.kucingselfie.kadesubmission.model.DetailMatch
import javax.inject.Inject

class DetailMatchViewModel @Inject constructor(repo: MatchRepository) : ViewModel() {
    private val _idEvent = MutableLiveData<String>()
    val idEvent: LiveData<String> get() = _idEvent

    private val _teamHomeId = MutableLiveData<String>()
    val teamHomeId: LiveData<String> get() = _teamHomeId

    private val _teamAwayId = MutableLiveData<String>()
    val teamAwayId: LiveData<String> get() = _teamAwayId

    fun setIdEvent(id: String) {
        _idEvent.value = id
    }

    fun setTeamHomeId(id: String) {
        _teamHomeId.value = id
    }

    fun setTeamAwayId(id: String) {
        _teamAwayId.value = id
    }

    val detailMatch: LiveData<Result<List<DetailMatch>>> = Transformations.switchMap(_idEvent) {
        repo.getDetailMatch(it)
    }

    val detailHomeTeam: LiveData<Result<List<DetailTeam>>> = Transformations.switchMap(_teamHomeId) {
        repo.getDetailHomeTeam(it)
    }

    val detailAwayTeam: LiveData<Result<List<DetailTeam>>> = Transformations.switchMap(_teamAwayId) {
        repo.getDetailAwayTeam(it)
    }
}
