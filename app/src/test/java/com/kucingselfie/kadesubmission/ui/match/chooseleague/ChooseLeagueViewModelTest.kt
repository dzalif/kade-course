package com.kucingselfie.kadesubmission.ui.match.chooseleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.FakeRemoteData
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ChooseLeagueViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: ListLeagueViewModel? = null
    private val repo = mock(LeagueRepository::class.java)
    private val leagues = FakeRemoteData.getDummyLeagues()

    @Before
    fun setUp() {
        viewModel = ListLeagueViewModel(repo)
    }

    @Test
    fun observeListLeague() {
        val listLeagueResult = MutableLiveData<Result<List<League>>>()
        listLeagueResult.postValue(Result.Success(leagues))
        `when`(repo.getListLeagues()).thenReturn(listLeagueResult)
        val observer = mock(Observer::class.java)
        @Suppress("UNCHECKED_CAST")
        viewModel?.leagues?.observeForever(observer as Observer<in Result<List<League>>>)
        Mockito.verify(repo).getListLeagues()
    }
}