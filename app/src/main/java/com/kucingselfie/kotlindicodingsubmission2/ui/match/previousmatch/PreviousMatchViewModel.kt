package com.kucingselfie.kotlindicodingsubmission2.ui.match.previousmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kotlindicodingsubmission2.api.TheSportsApi
import com.kucingselfie.kotlindicodingsubmission2.model.Match
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class PreviousMatchViewModel : ViewModel() {

    private val _status = MutableLiveData<Result>()
    val status: LiveData<Result>
        get() = _status

    private val _nextMatch = MutableLiveData<List<Match>>()
    val nextMatch: LiveData<List<Match>>
        get() = _nextMatch

    private var vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun getPreviousMatch(idLeague: String) {
        coroutineScope.launch {
            val getDetailDeferred = TheSportsApi.retrofitService.getPreviousMatch(idLeague.toInt())
            try {
                _status.value = Result.LOADING
                val listResult = getDetailDeferred.await()
                _nextMatch.value = listResult.events
                _status.value = Result.SUCCESS
            } catch (e: Exception) {
                _status.value = Result.ERROR
                _nextMatch.value = mutableListOf()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}