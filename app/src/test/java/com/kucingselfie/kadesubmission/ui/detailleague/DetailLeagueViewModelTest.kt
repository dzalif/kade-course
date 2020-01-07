package com.kucingselfie.kadesubmission.ui.detailleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.data.FakeRemoteData
import com.kucingselfie.kadesubmission.data.LeagueRepository
import com.kucingselfie.kadesubmission.model.League
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
class DetailLeagueViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: DetailLeagueViewModel? = null
    private val repo = mock(LeagueRepository::class.java)
    private val leagueResponse = FakeRemoteData.getDummyLeagues()
    private val idLeague = leagueResponse[0].id
    private val detailLeagueResponse = FakeRemoteData.getDummyDetailLeague()

    @Before
    fun setUp() {
        viewModel = DetailLeagueViewModel(repo)
    }

    @Test
    fun observeDetailLeague() {
        val detailResult = MutableLiveData<Result<List<League>>>()
        viewModel?.setIdLeague(idLeague)
        val resultSuccess = Result.Success(detailLeagueResponse)
        detailResult.postValue(resultSuccess)
        `when`(repo.getDetailLeague(idLeague)).thenReturn(detailResult)
        val observer = mock<Observer<Result<List<League>>>>()
        viewModel?.detailLeague?.observeForever(observer)
        verify(observer).onChanged(resultSuccess)
        val resultData = viewModel?.detailLeague?.value?.data

        assertNotNull(resultData)
        assertEquals(detailLeagueResponse[0].id, resultData?.get(0)?.id)
        assertEquals(detailLeagueResponse[0].description, resultData?.get(0)?.description)
    }
}