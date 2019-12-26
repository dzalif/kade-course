package com.kucingselfie.kotlindicodingsubmission2.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kucingselfie.kotlindicodingsubmission2.api.response.ListLeagueResponse
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague
import com.kucingselfie.kotlindicodingsubmission2.ui.searchevent.SearchViewModel
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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