package com.kucingselfie.kadesubmission.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kucingselfie.kadesubmission.BuildConfig
import com.kucingselfie.kadesubmission.api.response.*
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
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
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface ApiService {
    @GET("search_all_leagues.php")
    fun getListLeague(@Query("c") c: String, @Query("s") s: String) : Call<ListLeagueResponse>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") idleague: Int) : Call<DetailLeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") idleague: Int) : Call<NextMatchResponse>

    @GET("eventspastleague.php")
    fun getPreviousMatch(@Query("id") idleague: Int) : Call<PreviousMatchResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") idevent: Int) : Deferred<DetailMatchResponse>

    @GET("searchevents.php")
    fun searchEvents(@Query("e") query: String, @Query("strSport") s: String) : Call<SearchResponse>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String) : Deferred<DetailTeamResponse>
}

object TheSportsApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}