package com.kucingselfie.kadesubmission.ui.listleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import com.kucingselfie.kadesubmission.util.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class ListLeagueViewModel @Inject constructor(repository: LeagueRepository) : ViewModel() {
    private val _query = MutableLiveData<String>()
    val query: LiveData<String> get() = _query

    fun setQuery(query: String) {
        _query.value = query
    }

    val leagues: LiveData<Result<List<League>>> = repository.getListLeagues()

    val resultSearch: LiveData<Result<List<Search>>> = Transformations.switchMap(_query) {
        repository.search(it)
    }
}