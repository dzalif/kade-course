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
        remote.getNextMatch(id, object : RemoteRepository.LoadNextMatchCallback {
            override fun onSuccess(response: MutableList<Match>) {
                nextMatch.postValue(Result.Success(response))
            }

            override fun onError(message: String?) {
                message?.let {
                    nextMatch.postValue(Result.Error(message, null))
                }
            }
        })
        return nextMatch
    }
}