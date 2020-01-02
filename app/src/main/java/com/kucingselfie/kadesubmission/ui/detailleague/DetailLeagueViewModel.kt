package com.kucingselfie.kadesubmission.ui.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.TheSportsApi
import com.kucingselfie.kadesubmission.model.League
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailLeagueViewModel : ViewModel() {

//    private val _status = MutableLiveData<Result>()
//    val status: LiveData<Result>
//            get() = _status

    private val _detailLeague = MutableLiveData<List<League>>()
    val league: LiveData<List<League>>
    get() = _detailLeague

    private var vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun getDetailLeague(idLeague: String) {
        coroutineScope.launch {
            val getDetailDeferred = TheSportsApi.retrofitService.getDetailLeague(idLeague.toInt())
//            try {
//                _status.value = Result.LOADING
//                val listResult = getDetailDeferred.await()
//                _detailLeague.value = listResult.leagues
//                _status.value = Result.SUCCESS
//            } catch (e: Exception) {
//                _status.value = Result.ERROR
//                _detailLeague.value = mutableListOf()
//            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}