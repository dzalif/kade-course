package com.kucingselfie.kadesubmission.ui.detailmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.FakeRemoteData
import com.kucingselfie.kadesubmission.data.MatchRepository
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.ui.detailleague.DetailLeagueViewModel
import com.kucingselfie.kadesubmission.util.mock
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class DetailMatchViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: DetailMatchViewModel? = null
    private val repo = mock(MatchRepository::class.java)
    private val nextMatchResponse = FakeRemoteData.getDummyNextMatch()
    private val idMatch = nextMatchResponse[0].id
    private val detailMatchResponse = FakeRemoteData.getDummyDetailMatch()

    @Before
    fun setUp() {
        viewModel = DetailMatchViewModel(repo)
    }

    @Test
    fun observeDetailMatch() {
        val detailResult = MutableLiveData<Result<List<DetailMatch>>>()
        viewModel?.setIdEvent(idMatch)
        val resultSuccess = Result.Success(detailMatchResponse)
        detailResult.postValue(resultSuccess)
        `when`(repo.getDetailMatch(idMatch)).thenReturn(detailResult)
        val observer = mock<Observer<Result<List<DetailMatch>>>>()
        viewModel?.detailMatch?.observeForever(observer)
        Mockito.verify(observer).onChanged(resultSuccess)
        val resultData = viewModel?.detailMatch?.value?.data

        assertNotNull(resultData)
        assertEquals(detailMatchResponse[0].idEvent, resultData?.get(0)?.idEvent)
        assertEquals(detailMatchResponse[0].eventName, resultData?.get(0)?.eventName)
    }
}