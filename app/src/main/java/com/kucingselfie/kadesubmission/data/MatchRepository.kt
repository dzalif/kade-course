package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.model.Match
import javax.inject.Inject

class MatchRepository @Inject constructor(private val remote: RemoteRepository) : MatchDataSource {
    override fun getNextMatch(id: String): LiveData<Result<List<Match>>> {
        val nextMatch = MutableLiveData<Result<List<Match>>>()
        nextMatch.postValue(Result.Loading(null))
        remote.getNextMatch(id, object : LoadNextMatchCallback {
            override fun onSuccess(response: List<Match>) {
                nextMatch.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                nextMatch.postValue(Result.Error(message, null))
            }
        })
        return nextMatch
    }

    override fun getPreviousMatch(id: String): LiveData<Result<List<Match>>> {
        val previousMatch = MutableLiveData<Result<List<Match>>>()
        previousMatch.postValue(Result.Loading(null))
        remote.getPreviousMatch(id, object : LoadPreviousMatchCallback {
            override fun onSuccess(response: List<Match>) {
                previousMatch.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                previousMatch.postValue(Result.Error(message, null))
            }

        })
        return previousMatch
    }
}