package com.kucingselfie.kotlindicodingsubmission2.ui.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kotlindicodingsubmission2.api.TheSportsApi
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailLeagueViewModel : ViewModel() {

    private val _status = MutableLiveData<Result>()
    val status: LiveData<Result>
            get() = _status

    private val _detailLeague = MutableLiveData<List<DetailLeague>>()
    val detailLeague: LiveData<List<DetailLeague>>
    get() = _detailLeague

    private var vmJob = Job()
    val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun getDetailLeague() {
        coroutineScope.launch {
            val getDetailDeferred = TheSportsApi.retrofitService.getDetailLeague(4328)
            try {
                _status.value = Result.LOADING
                val listResult = getDetailDeferred.await()
                _detailLeague.value = listResult.leagues
                _status.value = Result.SUCCESS
            } catch (e: Exception) {
                _status.value = Result.ERROR
                _detailLeague.value = mutableListOf()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}