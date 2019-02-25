package com.fiqri.haripertama.restApi;

import com.fiqri.haripertama.model.ResponseEvents;
import com.fiqri.haripertama.model.ResponsePlayers;
import com.fiqri.haripertama.model.ResponseTeams;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("eventspastleague.php")
    Call<ResponseEvents> getMatchPastLeague(
            @Query("id") String id);

    @GET("eventsnextleague.php")
    Call<ResponseEvents> getMatchNextLeague(
            @Query("id") String id);

    @GET("lookup_all_teams.php")
    Call<ResponseTeams> getAllTeams(
            @Query("id") String id);

    @GET("lookup_all_players.php")
    Call<ResponsePlayers> getAllPlayers(
            @Query("id") String id);
}
