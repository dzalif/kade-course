package com.kucingselfie.kadesubmission.api

import com.google.gson.Gson
import com.kucingselfie.kadesubmission.api.response.ListLeagueResponse
import com.kucingselfie.kadesubmission.model.DetailLeague
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ApiServiceTest {

    @Mock
    lateinit var apiService: ApiService

    @Mock
    private lateinit var apiResponse: Deferred<ListLeagueResponse>

    @Mock
    private lateinit var gson: Gson

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getListLeague() {
        val listLeague = mutableListOf<DetailLeague>()
        val response = ListLeagueResponse(listLeague)
        runBlocking {
            Mockito.`when`(apiService.getListLeague(anyString(), anyString())).thenReturn(
                apiResponse
            )
            Mockito.`when`(apiResponse.await()).thenReturn(response)
            Mockito.`when`(gson.fromJson("", ListLeagueResponse::class.java)).thenReturn(
                response
            )
        }
    }
}