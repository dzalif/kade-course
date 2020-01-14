package com.kucingselfie.kadesubmission.ui.match.nextmatch

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

class NextMatchViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: NextMatchViewModel? = null
    private val repo = mock(MatchRepository::class.java)
    private val leagues = FakeRemoteData.getDummyLeagues()
    private val idLeague = leagues[0].id
    private val nextMatchResponse = FakeRemoteData.getDummyNextMatch()

    @Before
    fun setUp() {
        viewModel = NextMatchViewModel(repo)
    }

    @Test
    fun observeNextMatch() {
        val matchResult = MutableLiveData<Result<List<Match>>>()
        viewModel?.setIdLeague(idLeague)
        val resultSuccess = Result.Success(nextMatchResponse)
        matchResult.postValue(resultSuccess)
        `when`(repo.getNextMatch(idLeague)).thenReturn(matchResult)
        val observer = mock<Observer<Result<List<Match>>>>()
        viewModel?.nextMatch?.observeForever(observer)
        verify(observer).onChanged(resultSuccess)
        val resultData = viewModel?.nextMatch?.value?.data

        assertNotNull(resultData)
        verify(repo).getNextMatch(idLeague)
    }
}