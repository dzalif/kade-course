package com.kucingselfie.kadesubmission.data.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kucingselfie.kadesubmission.data.FakeRemoteData
import com.kucingselfie.kadesubmission.data.LoadDetailLeagueCallback
import com.kucingselfie.kadesubmission.data.LoadListLeagueCallback
import com.kucingselfie.kadesubmission.data.SearchMatchCallback
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.util.LiveDataTestUtil
import com.kucingselfie.kadesubmission.util.safeEq
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LeagueRepositoryTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val fakeRepository =
        FakeLeagueRepository(remote)
    private val leagues = FakeRemoteData.getDummyLeagues()
    private val leagueId = leagues[0].id
    private val leagueDetailResponse =
        FakeRemoteData.getDummyDetailLeague()
    private val searchResponse =
        FakeRemoteData.getDummySearchResult()

    @Test
    fun getLeagues() {
        doAnswer {
            val callback = it.arguments[0] as LoadListLeagueCallback
            callback.onSuccess(leagues)
            null
        }.`when`(remote).getListLeagues(any(LoadListLeagueCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getListLeagues())
        verify(remote, times(1)).getListLeagues(any(LoadListLeagueCallback::class.java))

        assertNotNull(result)
        assertEquals(leagues.size, result.data?.size)
    }

    @Test
    fun getDetailLeague() {
        doAnswer {
            val callback = it.arguments[1] as LoadDetailLeagueCallback
            callback.onSuccess(leagueDetailResponse)
            null
        }.`when`(remote).getDetailLeague(eq(leagueId), any(LoadDetailLeagueCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getDetailLeague(leagueId))
        verify(remote, times(1)).getDetailLeague(eq(leagueId), any(LoadDetailLeagueCallback::class.java))

        assertNotNull(result)
        assertEquals(leagueId, result.data?.get(0)?.id)
        assertEquals(leagues[0].description, result.data?.get(0)?.description)
    }

    @Test
    fun getSearchResult() {
        doAnswer {
            val callback = it.arguments[1] as SearchMatchCallback
            callback.onSuccess(searchResponse)
            null
        }.`when`(remote).search(safeEq("arsenal"), any(SearchMatchCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.search("arsenal"))
        verify(remote, times(1)).search(safeEq("arsenal"), any(SearchMatchCallback::class.java))

        assertNotNull(result)
        assertEquals(searchResponse.size, result.data?.size)
    }
}