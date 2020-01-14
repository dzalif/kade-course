package com.kucingselfie.kadesubmission.data.repository.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.*
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Standing
import com.kucingselfie.kadesubmission.model.Team
import com.kucingselfie.kadesubmission.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class MatchRepository @Inject constructor(private val remote: RemoteRepository) :
    MatchDataSource {
    override fun getNextMatch(id: String): LiveData<Result<List<Match>>> {
        val nextMatch = MutableLiveData<Result<List<Match>>>()
        nextMatch.postValue(Result.Loading(null))
        remote.getNextMatch(id, object :
            LoadNextMatchCallback {
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
        remote.getPreviousMatch(id, object :
            LoadPreviousMatchCallback {
            override fun onSuccess(response: List<Match>) {
                previousMatch.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                previousMatch.postValue(Result.Error(message, null))
            }

        })
        return previousMatch
    }

    override fun getDetailMatch(id: String): LiveData<Result<List<DetailMatch>>> {
        val detailMatch = MutableLiveData<Result<List<DetailMatch>>>()
        detailMatch.postValue(Result.Loading(null))
        remote.getDetailMatch(id, object :
            LoadDetailMatchCallback {
            override fun onSuccess(response: List<DetailMatch>) {
                detailMatch.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                detailMatch.postValue(Result.Error(message, null))
            }

        })
        return detailMatch
    }

    override fun getDetailHomeTeam(id: String): LiveData<Result<List<DetailTeam>>> {
        val detailHome = MutableLiveData<Result<List<DetailTeam>>>()
        detailHome.postValue(Result.Loading(null))
        remote.getDetailHomeTeam(id, object :
            LoadDetailTeamCallback {
            override fun onSuccess(response: List<DetailTeam>) {
                detailHome.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                detailHome.postValue(Result.Error(message, null))
            }
        })
        return detailHome
    }

    override fun getDetailAwayTeam(id: String): LiveData<Result<List<DetailTeam>>> {
        val detailAway = MutableLiveData<Result<List<DetailTeam>>>()
        detailAway.postValue(Result.Loading(null))
        remote.getDetailAwayTeam(id, object :
            LoadDetailTeamCallback {
            override fun onSuccess(response: List<DetailTeam>) {
                detailAway.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                detailAway.postValue(Result.Error(message, null))
            }
        })
        return detailAway
    }

    override fun getStandings(id: String): LiveData<Result<List<Standing>>> {
        val standings = MutableLiveData<Result<List<Standing>>>()
        standings.postValue(Result.Loading(null))
        remote.getStandings(id, object: LoadStandingCallback {
            override fun onSuccess(response: List<Standing>) {
                standings.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                standings.postValue(Result.Error(message, null))
            }
        })
        return standings
    }

    override fun getListTeam(id: String): LiveData<Result<List<Team>>> {
        val teams = MutableLiveData<Result<List<Team>>>()
        teams.postValue(Result.Loading(null))
        remote.getListTeam(id, object : LoadListTeamCallback {
            override fun onSuccess(response: List<Team>) {
                teams.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                teams.postValue(Result.Error(message, null))
            }
        })
        return teams
    }

    override fun searchTeam(query: String): LiveData<Result<List<Team>>> {
        val teams = MutableLiveData<Result<List<Team>>>()
        teams.postValue(Result.Loading(null))
        remote.searchTeam(query, object :
            LoadListTeamCallback {
            override fun onSuccess(response: List<Team>) {
                teams.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                teams.postValue(Result.Error(message, null))
            }
        })
        return teams
    }
}