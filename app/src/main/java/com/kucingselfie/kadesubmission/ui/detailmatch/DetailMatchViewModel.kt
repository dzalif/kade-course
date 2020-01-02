package com.kucingselfie.kadesubmission.ui.detailmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.TheSportsApi
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.util.toGMT7
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeoutException

class DetailMatchViewModel : ViewModel() {

    companion object {
        const val GK = "(GK)"
        const val FW = "(FW)"
        const val MD = "(MD)"
        const val DF = "(DF)"
    }

//    private val _status = MutableLiveData<Result>()
//    val status: LiveData<Result>
//        get() = _status

    private val _detailMatch = MutableLiveData<DetailMatch>()
    val detailMatch: LiveData<DetailMatch>
        get() = _detailMatch

    private val _detailHomeTeam = MutableLiveData<List<DetailTeam>>()
    val detailHomeTeam: LiveData<List<DetailTeam>> get() = _detailHomeTeam

    private val _detailAwayTeam = MutableLiveData<List<DetailTeam>>()
    val detailAwayTeam: LiveData<List<DetailTeam>> get() = _detailAwayTeam

    private var vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun getDetailMatch(idEvent: String, teamHomeId: String, teamAwayId: String) {
        coroutineScope.launch {
            val getDetailDeferred = TheSportsApi.retrofitService.getDetailMatch(idEvent.toInt())
//            try {
//                _status.value = Result.LOADING
//                val listResult = getDetailDeferred.await()
//                val event = setEventData(listResult.events)
//                _detailMatch.value = event
//            } catch (e: Throwable) {
//                when(e) {
//                    is IOException -> _status.value = Result.NO_INTERNET_CONNECTION
//                    is TimeoutException -> _status.value = Result.TIMEOUT
//                    else -> _status.value = Result.UNKNOWN_ERROR
//                }
//                _status.value = Result.ERROR
//                _detailMatch.value = null
//            }
        }

        getDetailHomeTeam(teamHomeId)
        getDetailAwayTeam(teamAwayId)
    }

    private fun setEventData(events: List<DetailMatch>) : DetailMatch {
        return DetailMatch(
            events[0].dateEvent,
            events[0].idEvent,
            events[0].eventName,
            events[0].intAwayScore ?: "0",
            events[0].intHomeScore ?: "0",
            events[0].strAwayTeam,
            events[0].strHomeTeam,
            events[0].strTime?.toGMT7() ?: "",
            events[0].strHomeFormation ?: "",
            events[0].strAwayFormation ?: "",
            events[0].strHomeGK + GK,
            events[0].strHomeFW + FW,
            events[0].strHomeMD + MD,
            events[0].strHomeDF + DF,
            events[0].strAwayGK + GK,
            events[0].strAwayFW + FW,
            events[0].strAwayMD + MD,
            events[0].strAwayDF + DF
        )
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

    fun getDetailHomeTeam(teamHomeId: String) {
        coroutineScope.launch {
            val getDetailDeferred = TheSportsApi.retrofitService.getDetailTeam(teamHomeId)
//            try {
//                _status.value = Result.LOADING
//                val listResult = getDetailDeferred.await()
//                _detailHomeTeam.value = listResult.teams
//                if (listResult.teams.isEmpty()) {
//                    _status.value = Result.NO_DATA
//                } else {
//                    _status.value = Result.SUCCESS
//                }
//            } catch (e: Exception) {
//                _status.value = Result.ERROR
//                _detailHomeTeam.value = mutableListOf()
//            }
        }
    }

    fun getDetailAwayTeam(teamAwayId: String) {
        coroutineScope.launch {
            val getDetailDeferred = TheSportsApi.retrofitService.getDetailTeam(teamAwayId)
//            try {
//                _status.value = Result.LOADING
//                val listResult = getDetailDeferred.await()
//                _detailAwayTeam.value = listResult.teams
//                if (listResult.teams.isEmpty()) {
//                    _status.value = Result.NO_DATA
//                } else {
//                    _status.value = Result.SUCCESS
//                }
//            } catch (e: Exception) {
//                _status.value = Result.ERROR
//                _detailAwayTeam.value = mutableListOf()
//            }
        }
    }
}
