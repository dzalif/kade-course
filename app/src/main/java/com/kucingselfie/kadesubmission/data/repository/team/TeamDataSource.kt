package com.kucingselfie.kadesubmission.data.repository.team

import androidx.lifecycle.LiveData
import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.common.Result

interface TeamDataSource {
    fun getDetailTeam(id: String) : LiveData<Result<List<DetailTeam>>>
}