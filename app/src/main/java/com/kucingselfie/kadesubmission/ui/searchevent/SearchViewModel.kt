package com.kucingselfie.kadesubmission.ui.searchevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.TheSportsApi
import com.kucingselfie.kadesubmission.common.ENGLAND
import com.kucingselfie.kadesubmission.common.SOCCER
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SearchViewModel @Inject constructor(repository: LeagueRepository): ViewModel() {

//    private val _status = MutableLiveData<Result>()
//    val status: LiveData<Result>
//        get() = _status
//
//    private val _search = MutableLiveData<MutableList<Search>>()
//    val search: LiveData<MutableList<Search>>
//        get() = _search
//
//    private val _listLeague = MutableLiveData<List<League>>()
//    val listLeague: LiveData<List<League>>
//    get() = _listLeague
//
//    private var vmJob = Job()
//    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)
//
//    fun doSearch(query: String) {
//        coroutineScope.launch {
//            val result = TheSportsApi.retrofitService.searchEvents(query, SOCCER)
//            try {
//                _status.value = Result.LOADING
//                val listResult = result.await()
//                _search.value = listResult.event
//                _status.value = Result.SUCCESS
//            } catch (e: Exception) {
//                _status.value = Result.ERROR
//                _search.value = mutableListOf()
//            }
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        vmJob.cancel()
//    }
//
//    fun getListLeague() {
//        coroutineScope.launch {
//            val result = TheSportsApi.retrofitService.getListLeague(ENGLAND, SOCCER)
//            try {
//                _status.value = Result.LOADING
//                val listResult = result.await()
//                _listLeague.value = listResult.countries
//                _status.value = Result.SUCCESS
//            } catch (e: Exception) {
//                _status.value = Result.ERROR
//                _search.value = mutableListOf()
//            }
//        }
//    }

}