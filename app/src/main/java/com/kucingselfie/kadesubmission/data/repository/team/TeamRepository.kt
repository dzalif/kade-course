package com.kucingselfie.kadesubmission.data.repository.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.LoadDetailTeamCallback
import com.kucingselfie.kadesubmission.data.LoadNextMatchCallback
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Team
import com.kucingselfie.kadesubmission.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class TeamRepository @Inject constructor(private val remote: RemoteRepository) : TeamDataSource {
    override fun getDetailTeam(id: String): LiveData<Result<List<DetailTeam>>> {
        val teams = MutableLiveData<Result<List<DetailTeam>>>()
        teams.postValue(Result.Loading(null))
        remote.getDetailTeam(id, object :
            LoadDetailTeamCallback {
            override fun onSuccess(response: List<DetailTeam>) {
                teams.postValue(Result.Success(response))
            }

            override fun onError(message: String) {
                teams.postValue(Result.Error(message, null))
            }
        })
        return teams
    }
}