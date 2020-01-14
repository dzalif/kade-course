package com.kucingselfie.kadesubmission.ui.listleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.FakeRemoteData
import com.kucingselfie.kadesubmission.data.repository.league.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import com.kucingselfie.kadesubmission.util.mock
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class ListLeagueViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: ListLeagueViewModel? = null
    private val repo = mock(LeagueRepository::class.java)
    private val searchResponse = FakeRemoteData.getDummySearchResult()

    @Before
    fun setUp() {
        viewModel = ListLeagueViewModel(repo)
    }

    @Test
    fun observeListLeague() {
        val listLeagueResult = MutableLiveData<Result<List<League>>>()
        val leagues = FakeRemoteData.getDummyLeagues()
        listLeagueResult.postValue(Result.Success(leagues))
        `when`(repo.getListLeagues()).thenReturn(listLeagueResult)
        val observer = mock(Observer::class.java)
        @Suppress("UNCHECKED_CAST")
        viewModel?.leagues?.observeForever(observer as Observer<in Result<List<League>>>)
        verify(repo).getListLeagues()
    }

    @Test
    fun observeSearch() {
        val searchResult = MutableLiveData<Result<List<Search>>>()
        viewModel?.setQuery("arsenal")
        val resultSuccess = Result.Success(searchResponse)
        searchResult.postValue(resultSuccess)
        `when`(repo.search("arsenal")).thenReturn(searchResult)
        val observer = mock<Observer<Result<List<Search>>>>()
        viewModel?.resultSearch?.observeForever(observer)
        verify(observer).onChanged(resultSuccess)
        val resultData = viewModel?.resultSearch?.value?.data

        assertNotNull(resultData)
        assertEquals(searchResponse[0].idLeague, resultData?.get(0)?.idLeague)
        assertEquals(searchResponse[0].dateEvent, resultData?.get(0)?.dateEvent)
    }
}