package com.example.android.sqliteweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.sqliteweather.data.FiveDayForecast;
import com.example.android.sqliteweather.data.FiveDayForecastRepository;
import com.example.android.sqliteweather.data.GenreList;
import com.example.android.sqliteweather.data.LanguageList;
import com.example.android.sqliteweather.data.LoadingStatus;
import com.example.android.sqliteweather.data.MovieRepository;

public class MovieViewModel extends ViewModel {
    private MovieRepository repository;
    private LiveData<LanguageList> languageList;
    private LiveData<GenreList> genreList;
    private LiveData<LoadingStatus> loadingStatus;

    public MovieViewModel() {
        this.repository = new MovieRepository();
        genreList = repository.getGenres();
        languageList = repository.getLanguage();
        loadingStatus = repository.getLoadingStatus();
    }

    public LiveData<GenreList> getGenres() {
        return this.genreList;
    }

    public LiveData<LanguageList> getLanguageList() {
        return this.languageList;
    }

    public LiveData<LoadingStatus> getLoadingStatus() {
        return this.loadingStatus;
    }

    public void loadMovies(int mode, String apiKey) {
        this.repository.loadMovieDatabase(mode, apiKey);
    }
}
