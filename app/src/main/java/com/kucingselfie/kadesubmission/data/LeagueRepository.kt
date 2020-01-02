package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import javax.inject.Inject

class LeagueRepository @Inject constructor(private val remote: RemoteRepository) : FootballDataSource {
    override fun search(query: String): LiveData<Result<List<Search>>> {
        val matches = MutableLiveData<Result<List<Search>>>()
        matches.postValue(Result.Loading(null))
        remote.search(query, object : RemoteRepository.SearchMatchCallback {
            override fun onSuccess(response: MutableList<Search>) {
                matches.postValue(Result.Success(response))
            }

            override fun onError(message: String?) {
                message?.let {
                    matches.postValue(Result.Error(message, null))
                }
            }

        })
        return matches
    }

    override fun getListLeagues(): LiveData<Result<List<League>>> {
        val leagues = MutableLiveData<Result<List<League>>>()
        leagues.postValue(Result.Loading(null))
        remote.getListLeagues(object : RemoteRepository.LoadListLeagueCallback {
            override fun onSuccess(response: List<League>) {
                leagues.postValue(Result.Success(response))
            }

            override fun onError(message: String?) {
                message?.let {
                    leagues.postValue(Result.Error(message, null))
                }
            }
        })
        return leagues
    }

}