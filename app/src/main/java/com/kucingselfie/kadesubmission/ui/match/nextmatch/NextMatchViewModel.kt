package com.kucingselfie.kadesubmission.ui.match.nextmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.MatchRepository
import com.kucingselfie.kadesubmission.model.Match
import javax.inject.Inject

class NextMatchViewModel @Inject constructor(repo: MatchRepository) : ViewModel() {

    private val _idLeague = MutableLiveData<String>()
    val idLeague get() = _idLeague

    fun setIdLeague(id: String) {
       _idLeague.value = id
    }

    val nextMatch: LiveData<Result<List<Match>>> = Transformations.switchMap(_idLeague) {
        repo.getNextMatch(it)
    }

}