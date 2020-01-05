package com.kucingselfie.kadesubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import javax.inject.Inject

class LeagueRepository @Inject constructor(private val remote: RemoteRepository) : LeagueDataSource {
    override fun getDetailLeague(id: String): LiveData<Result<List<League>>> {
        val detailLeague = MutableLiveData<Result<List<League>>>()
        detailLeague.postValue(Result.Loading(null))
        remote.getDetailLeague(id, object : LoadDetailLeagueCallback {
            override fun onSuccess(response: List<League>) {
                detailLeague.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                detailLeague.postValue(Result.Error(message, null))
            }
        })
        return detailLeague
    }

    override fun search(query: String): LiveData<Result<List<Search>>> {
        val matches = MutableLiveData<Result<List<Search>>>()
        matches.postValue(Result.Loading(null))
        remote.search(query, object : SearchMatchCallback {
            override fun onSuccess(response: List<Search>) {
                matches.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                matches.postValue(Result.Error(message, null))
            }

        })
        return matches
    }

    override fun getListLeagues(): LiveData<Result<List<League>>> {
        val leagues = MutableLiveData<Result<List<League>>>()
        leagues.postValue(Result.Loading(null))
        remote.getListLeagues(object : LoadListLeagueCallback {
            override fun onError(message: String) {
                message?.let {
                    leagues.postValue(Result.Error(message, null))
                }
            }

            override fun onSuccess(response: List<League>) {
                leagues.postValue(Result.Success(response))
            }
        })
        return leagues
    }
}