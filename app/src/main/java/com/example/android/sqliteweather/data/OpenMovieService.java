package com.example.android.sqliteweather.data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMovieService {
    @GET("configuration/languages")
    Call<ArrayList<LanguageData>> fetchLanguages(
            @Query("api_key") String apikey
    );

    @GET("genre/movie/list")
    Call<GenreList> fetchGenres(
            @Query("api_key") String apikey
    );



}
