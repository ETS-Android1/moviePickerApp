package com.example.android.sqliteweather.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMovieService {
    @GET("genre/movie/list")
    Call<GenreList> fetchGenres(
            @Query("api_key") String apikey
    );

    @GET("configuration/languages")
    Call<LanguageList> fetchLanguages(
            @Query("api_key") String apikey
    );

}
