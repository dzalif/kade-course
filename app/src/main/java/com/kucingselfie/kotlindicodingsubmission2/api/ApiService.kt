package com.kucingselfie.kotlindicodingsubmission2.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kucingselfie.kotlindicodingsubmission2.BuildConfig
import com.kucingselfie.kotlindicodingsubmission2.api.response.*
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) this.level = HttpLoggingInterceptor.Level.BODY
}

val client: OkHttpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(interceptor)
}.build()

var gson: Gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(client)
    .build()

interface ApiService {
    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") idleague: Int) : Deferred<DetailLeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") idleague: Int) : Deferred<NextMatchResponse>

    @GET("eventspastleague.php")
    fun getPreviousMatch(@Query("id") idleague: Int) : Deferred<PreviousMatchResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") idevent: Int) : Deferred<DetailMatchResponse>

    @GET("searchevents.php")
    fun searchEvents(@Query("e") query: String) : Deferred<SearchResponse>
}

object TheSportsApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}