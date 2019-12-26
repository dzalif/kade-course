package com.kucingselfie.kadesubmission.ui.match.chooseleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.TheSportsApi
import com.kucingselfie.kadesubmission.common.ENGLAND
import com.kucingselfie.kadesubmission.common.SOCCER
import com.kucingselfie.kadesubmission.model.DetailLeague
import com.kucingselfie.kadesubmission.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class ChooseLeagueViewModel : ViewModel() {

    private val _status = MutableLiveData<Result>()
    val status: LiveData<Result>
        get() = _status

    private val _listLeague = MutableLiveData<List<DetailLeague>>()
    val listLeague: LiveData<List<DetailLeague>>
        get() = _listLeague

    private var vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun getListLeague() {
        coroutineScope.launch {
            val result = TheSportsApi.retrofitService.getListLeague(ENGLAND, SOCCER)
            try {
                _status.value = Result.LOADING
                val listResult = result.await()
                _listLeague.value = listResult.countrys
                _status.value = Result.SUCCESS
            } catch (e: Exception) {
                _status.value = Result.ERROR
            }
        }
    }

}