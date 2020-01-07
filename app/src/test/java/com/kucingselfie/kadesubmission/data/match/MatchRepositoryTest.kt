package com.kucingselfie.kadesubmission.data.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kucingselfie.kadesubmission.data.*
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import com.kucingselfie.kadesubmission.util.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MatchRepositoryTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val fakeRepository = FakeMatchRepository(remote)
    private val nextMatchResponse = FakeRemoteData.getDummyNextMatch()
    private val previousMatchResponse = FakeRemoteData.getDummyPreviousMatch()
    val leagues = FakeRemoteData.getDummyLeagues()
    private val idLeague = leagues[0].id
    private val idMatch = nextMatchResponse[0].id
    private val idHomeTeam = nextMatchResponse[0].teamHomeId
    private val idAwayTeam = nextMatchResponse[0].teamAwayId
    private val detailMatchResponse = FakeRemoteData.getDummyDetailMatch()
    private val detailTeamHomeResponse = FakeRemoteData.getDummyDetailTeamHome()
    private val detailTeamAwayResponse = FakeRemoteData.getDummyDetailTeamAway()

    @Test
    fun getNextMatch() {
        doAnswer {
            val callback = it.arguments[1] as LoadNextMatchCallback
            callback.onSuccess(nextMatchResponse)
            null
        }.`when`(remote).getNextMatch(eq(idLeague), any(LoadNextMatchCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getNextMatch(idLeague))
        verify(remote, times(1))
            .getNextMatch(eq(idLeague), any(LoadNextMatchCallback::class.java))

        assertNotNull(result)
        assertEquals(nextMatchResponse.size, result.data?.size)
    }

    @Test
    fun getPreviousMatch() {
        doAnswer {
            val callback = it.arguments[1] as LoadPreviousMatchCallback
            callback.onSuccess(previousMatchResponse)
            null
        }.`when`(remote).getPreviousMatch(eq(idLeague), any(LoadPreviousMatchCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getPreviousMatch(idLeague))
        verify(remote, times(1))
            .getPreviousMatch(eq(idLeague), any(LoadPreviousMatchCallback::class.java))

        assertNotNull(result)
        assertEquals(previousMatchResponse.size, result.data?.size)
    }

    @Test
    fun getDetailMatch() {
        doAnswer {
            val callback = it.arguments[1] as LoadDetailMatchCallback
            callback.onSuccess(detailMatchResponse)
            null
        }.`when`(remote).getDetailMatch(eq(idMatch), any(LoadDetailMatchCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getDetailMatch(idMatch))
        verify(remote, times(1))
            .getDetailMatch(eq(idMatch), any(LoadDetailMatchCallback::class.java))

        assertNotNull(result)
        assertEquals(idMatch, result.data?.get(0)?.idEvent)
        assertEquals(detailMatchResponse[0].eventName, result.data?.get(0)?.eventName)
    }

    @Test
    fun getDetailHomeTeam() {
        doAnswer {
            val callback = it.arguments[1] as LoadDetailTeamCallback
            callback.onSuccess(detailTeamHomeResponse)
            null
        }.`when`(remote).getDetailHomeTeam(eq(idHomeTeam), any(LoadDetailTeamCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getDetailHomeTeam(idHomeTeam))
        verify(remote, times(1))
            .getDetailHomeTeam(eq(idHomeTeam), any(LoadDetailTeamCallback::class.java))

        assertNotNull(result)
        assertEquals(idHomeTeam, result.data?.get(0)?.teamId)
        assertEquals(detailTeamHomeResponse[0].strTeamBadge, result.data?.get(0)?.strTeamBadge)
    }

    @Test
    fun getDetailAwayTeam() {
        doAnswer {
            val callback = it.arguments[1] as LoadDetailTeamCallback
            callback.onSuccess(detailTeamAwayResponse)
            null
        }.`when`(remote).getDetailAwayTeam(eq(idAwayTeam), any(LoadDetailTeamCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getDetailAwayTeam(idAwayTeam))
        verify(remote, times(1))
            .getDetailAwayTeam(eq(idAwayTeam), any(LoadDetailTeamCallback::class.java))

        assertNotNull(result)
        assertEquals(idAwayTeam, result.data?.get(0)?.teamId)
        assertEquals(detailTeamAwayResponse[0].strTeamBadge, result.data?.get(0)?.strTeamBadge)
    }
}