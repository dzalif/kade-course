package com.kucingselfie.kadesubmission.ui.match.previousmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.FakeRemoteData
import com.kucingselfie.kadesubmission.data.repository.match.MatchRepository
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.util.mock
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class PreviousMatchViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: PreviousMatchViewModel? = null
    private val repo = mock(MatchRepository::class.java)
    private val leagues = FakeRemoteData.getDummyLeagues()
    private val idLeague = leagues[0].id
    private val previousMatchResponse = FakeRemoteData.getDummyPreviousMatch()

    @Before
    fun setUp() {
        viewModel = PreviousMatchViewModel(repo)
    }

    @Test
    fun observePreviousMatch() {
        val matchResult = MutableLiveData<Result<List<Match>>>()
        viewModel?.setIdLeague(idLeague)
        val resultSuccess = Result.Success(previousMatchResponse)
        matchResult.postValue(resultSuccess)
        `when`(repo.getPreviousMatch(idLeague)).thenReturn(matchResult)
        val observer = mock<Observer<Result<List<Match>>>>()
        viewModel?.previousMatch?.observeForever(observer)
        verify(observer).onChanged(resultSuccess)
        val resultData = viewModel?.previousMatch?.value?.data

        assertNotNull(resultData)
        verify(repo).getPreviousMatch(idLeague)
    }
}