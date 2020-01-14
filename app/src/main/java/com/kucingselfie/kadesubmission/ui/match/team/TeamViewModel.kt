package com.kucingselfie.kadesubmission.ui.match.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.repository.match.MatchRepository
import com.kucingselfie.kadesubmission.model.Team
import javax.inject.Inject

class TeamViewModel @Inject constructor(repository: MatchRepository) : ViewModel() {
    private val _idLeague = MutableLiveData<String>()
    val idLeague: LiveData<String> get() = _idLeague

    fun setIdLeague(id: String) {
        _idLeague.value = id
    }

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> get() = _query

    fun setQuery(query: String) {
        _query.value = query
    }

    val teams: LiveData<Result<List<Team>>> = Transformations.switchMap(_idLeague) {
        repository.getListTeam(it)
    }

    val resultSearch: LiveData<Result<List<Team>>> = Transformations.switchMap(_query) {
        repository.searchTeam(it)
    }
}
