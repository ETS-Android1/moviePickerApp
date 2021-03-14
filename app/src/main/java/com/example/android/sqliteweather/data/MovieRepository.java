package com.example.android.sqliteweather.data;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String TAG = MovieRepository.class.getSimpleName();
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private MutableLiveData<GenreList> genreList;
    private MutableLiveData<LanguageList> languageList;

    private String currentMovieName;

    private MutableLiveData<LoadingStatus> loadingStatus;


    private OpenMovieService openMovieService;

    public MovieRepository() {
        this.genreList = new MutableLiveData<>();
        this.genreList.setValue(null);

        this.languageList = new MutableLiveData<>();
        this.languageList.setValue(null);

        this.loadingStatus = new MutableLiveData<>();
        this.loadingStatus.setValue(LoadingStatus.SUCCESS);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(GenreData.class, new GenreData.JsonDeserializer())
                .registerTypeAdapter(LanguageList.class, new LanguageList.JsonDeserializer())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.openMovieService = retrofit.create(OpenMovieService.class);

    }

    public void loadMovieDatabase(int mode, String apiKey) {
        if (/*shouldFetchMovies()*/true) {
            Log.d(TAG, "Going into mode: " + mode);

            this.loadingStatus.setValue(LoadingStatus.LOADING);

            if(mode == 1){
                this.genreList.setValue(null);
                Call<GenreList> req = this.openMovieService.fetchGenres(apiKey);
                req.enqueue(new Callback<GenreList>() {
                    @Override
                    public void onResponse(Call<GenreList> call, Response<GenreList> response) {
                        if (response.code() == 200) {
                            genreList.setValue(response.body());
                            loadingStatus.setValue(LoadingStatus.SUCCESS);
                        } else {
                            loadingStatus.setValue(LoadingStatus.ERROR);
                            Log.d(TAG, "unsuccessful API request: " + call.request().url());
                            Log.d(TAG, "  -- response status code: " + response.code());
                            Log.d(TAG, "  -- response: " + response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreList> call, Throwable t) {
                        loadingStatus.setValue(LoadingStatus.ERROR);
                        Log.d(TAG, "unsuccessful API request: " + call.request().url());
                        t.printStackTrace();
                    }
                });
            }else if(mode == 2){
                this.languageList.setValue(null);
                Call<LanguageList> req = this.openMovieService.fetchLanguages(apiKey);
                req.enqueue(new Callback<LanguageList>() {
                    @Override
                    public void onResponse(Call<LanguageList> call, Response<LanguageList> response) {
                        if (response.code() == 200) {
                            languageList.setValue(response.body());
                            loadingStatus.setValue(LoadingStatus.SUCCESS);
                        } else {
                            loadingStatus.setValue(LoadingStatus.ERROR);
                            Log.d(TAG, "unsuccessful API request: " + call.request().url());
                            Log.d(TAG, "  -- response status code: " + response.code());
                            Log.d(TAG, "  -- response: " + response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<LanguageList> call, Throwable t) {
                        loadingStatus.setValue(LoadingStatus.ERROR);
                        Log.d(TAG, "unsuccessful API request: " + call.request().url());
                        t.printStackTrace();
                    }
                });
            }
        } else {
            Log.d(TAG, "using cached movie: DEFAULT");
        }
    }
    /*
    private boolean shouldFetchForecast(String location, String units) {
        /*
         * Fetch forecast if there isn't currently one stored.


    }
    */
}
