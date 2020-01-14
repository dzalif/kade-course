package com.kucingselfie.kadesubmission.ui.match.previousmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.repository.match.MatchRepository
import com.kucingselfie.kadesubmission.model.Match
import javax.inject.Inject

class PreviousMatchViewModel @Inject constructor(repo: MatchRepository) : ViewModel() {
    private val _idLeague = MutableLiveData<String>()
    val idLeague get() = _idLeague

    fun setIdLeague(id: String) {
        _idLeague.value = id
    }

    val previousMatch: LiveData<Result<List<Match>>> = Transformations.switchMap(_idLeague) {
        repo.getPreviousMatch(it)
    }
}