package com.kucingselfie.kotlindicodingsubmission2.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kucingselfie.kotlindicodingsubmission2.BuildConfig
import com.kucingselfie.kotlindicodingsubmission2.api.response.DetailLeagueResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.GsonBuilder
import com.google.gson.Gson



val client = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    })
    .build()

var gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(client)
    .baseUrl(BuildConfig.BASE_URL)
    .build()

interface ApiService {
    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") idleague: Int) : Deferred<DetailLeagueResponse>
}

object TheSportsApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}