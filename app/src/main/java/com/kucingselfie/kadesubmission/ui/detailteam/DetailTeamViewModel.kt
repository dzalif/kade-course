package com.kucingselfie.kadesubmission.ui.detailteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.repository.team.TeamRepository
import javax.inject.Inject

class DetailTeamViewModel @Inject constructor(repository: TeamRepository) : ViewModel() {
    private val _idTeam = MutableLiveData<String>()
    val idTeam: LiveData<String> get() = _idTeam

    fun setIdTeam(id: String) {
        _idTeam.value = id
    }

    private val _data = MutableLiveData<DetailTeam>()
    val data: LiveData<DetailTeam> get() = _data

    fun setData(data: DetailTeam?) {
        _data.value = data
    }

    fun getData() : DetailTeam? {
        return _data.value
    }

    val team: LiveData<Result<List<DetailTeam>>> = Transformations.switchMap(_idTeam) {
        repository.getDetailTeam(it)
    }
}
