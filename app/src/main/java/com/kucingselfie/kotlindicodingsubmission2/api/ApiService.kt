package com.kucingselfie.kotlindicodingsubmission2.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kucingselfie.kotlindicodingsubmission2.BuildConfig
import com.kucingselfie.kotlindicodingsubmission2.api.response.DetailLeagueResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY)
    .build()

interface ApiService {
    fun getDetailLeague(@Query("idLeague") idleague: Int) : Deferred<DetailLeagueResponse>
}

object TheSportsApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}