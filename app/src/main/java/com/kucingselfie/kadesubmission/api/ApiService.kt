package com.kucingselfie.kadesubmission.api

import com.kucingselfie.kadesubmission.api.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search_all_leagues.php")
    fun getListLeague(@Query("c") c: String, @Query("s") s: String) : Call<ListLeagueResponse>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") idLeague: Int) : Call<DetailLeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") idLeague: Int) : Call<NextMatchResponse>

    @GET("eventspastleague.php")
    fun getPreviousMatch(@Query("id") idLeague: Int) : Call<PreviousMatchResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") idEvent: Int) : Call<DetailMatchResponse>

    @GET("searchevents.php")
    fun searchEvents(@Query("e") query: String, @Query("strSport") s: String) : Call<SearchResponse>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String) : Call<DetailTeamResponse>

    @GET("lookuptable.php")
    fun getStandings(@Query("l") id: String) : Call<StandingResponse>

    @GET("lookup_all_teams.php")
    fun getListTeam(@Query("id") id: String) : Call<ListTeamResponse>

    @GET("searchteams.php")
    fun searchTeam(@Query("t") query: String) : Call<SearchTeamResponse>
}