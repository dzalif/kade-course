package com.kucingselfie.kadesubmission.ui.match.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.MatchRepository
import com.kucingselfie.kadesubmission.model.Standing
import javax.inject.Inject

class StandingsViewModel @Inject constructor(repository: MatchRepository) : ViewModel() {
    private val _idLeague = MutableLiveData<String>()
    val idLeague: LiveData<String> get() = _idLeague

    fun setIdLeague(id: String) {
        _idLeague.value = id
    }

    val standings: LiveData<Result<List<Standing>>> = Transformations.switchMap(_idLeague) {
        repository.getStandings(it)
    }
}
