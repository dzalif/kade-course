package com.kucingselfie.kotlindicodingsubmission2.ui.searchevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.kotlindicodingsubmission2.api.TheSportsApi
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.model.Search
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel : ViewModel() {

    private val _status = MutableLiveData<Result>()
    val status: LiveData<Result>
        get() = _status

    private val _search = MutableLiveData<MutableList<Search>>()
    val search: LiveData<MutableList<Search>>
        get() = _search

    private var vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun doSearch(query: String) {
        coroutineScope.launch {
            val result = TheSportsApi.retrofitService.searchEvents(query)
            try {
                _status.value = Result.LOADING
                val listResult = result.await()
                _search.value = listResult.event
                _status.value = Result.SUCCESS
            } catch (e: Exception) {
                _status.value = Result.ERROR
                _search.value = mutableListOf()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}