
package com.example.android.sqliteweather;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.sqliteweather.data.ForecastCity;
import com.example.android.sqliteweather.data.ForecastData;
import com.example.android.sqliteweather.data.GenreData;
import com.example.android.sqliteweather.data.MovieData;

public class MovieViewActivity extends AppCompatActivity {
    private static final String TAG = MovieViewActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE_DATA = "MovieViewActivity.MovieData";
    public static final String EXTRA_RV_DATA = "MovieViewActivity.RecyclerView";
    public static final String EXTRA_GENRE_ADAPTER = "MovieViewActivity.GenreAdapter";


    private MovieData movieData = null;
    private RecyclerView mainRV = null;
    private GenreAdapter genreAdapter = null;

    private Toast errorToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forecast_detail);
        setContentView(R.layout.activity_view_movie);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_MOVIE_DATA)) {
            this.movieData = (MovieData) intent.getSerializableExtra(EXTRA_MOVIE_DATA);

            Log.d(TAG, "this poster address going in: " + movieData.getPoster_path());
            ImageView posterIV = findViewById(R.id.iv_movie_icon);
            Glide.with(this)
                    .load(this.movieData.getPoster_path())
                    .into(posterIV);
            TextView movieName = findViewById(R.id.tv_movie_name);
            movieName.setText(movieData.getTitle());
        }

        if (intent != null && intent.hasExtra(EXTRA_RV_DATA)){
            this.mainRV = (RecyclerView) intent.getSerializableExtra(EXTRA_RV_DATA);
        }

        if (intent != null && intent.hasExtra(EXTRA_GENRE_ADAPTER)){
            this.genreAdapter = (GenreAdapter) intent.getSerializableExtra(EXTRA_GENRE_ADAPTER);
        }


//        if (intent != null && intent.hasExtra(EXTRA_FORECAST_CITY)) {
//            this.forecastCity = (ForecastCity)intent.getSerializableExtra(EXTRA_FORECAST_CITY);
//            TextView forecastCityTV = findViewById(R.id.tv_forecast_city);
//            forecastCityTV.setText(this.forecastCity.getName());
//        }
//
//        if (intent != null && intent.hasExtra(EXTRA_FORECAST_DATA)) {
//            this.forecastData = (ForecastData)intent.getSerializableExtra(EXTRA_FORECAST_DATA);
//
//            /*
//             * Load forecast icon into ImageView using Glide: https://bumptech.github.io/glide/
//             */
//            ImageView forecastIconIV = findViewById(R.id.iv_forecast_icon);
//            Glide.with(this)
//                    .load(this.forecastData.getIconUrl())
//                    .into(forecastIconIV);
//
//            TextView forecastDateTV = findViewById(R.id.tv_forecast_date);
//            Calendar date = OpenWeatherUtils.dateFromEpochAndTZOffset(
//                    forecastData.getEpoch(),
//                    forecastCity.getTimezoneOffsetSeconds()
//            );
//            forecastDateTV.setText(getString(
//                    R.string.forecast_date_time,
//                    getString(R.string.forecast_date, date),
//                    getString(R.string.forecast_time, date)
//            ));
//
//            String unitsPref = sharedPreferences.getString(
//                    getString(R.string.pref_units_key),
//                    getString(R.string.pref_units_default_value)
//            );
//            TextView lowTempTV = findViewById(R.id.tv_unfavorite_pass);
//            lowTempTV.setText(getString(
//                    R.string.forecast_temp,
//                    forecastData.getLowTemp(),
//                    /* get correct temperature unit for unit preference setting */
//                    OpenWeatherUtils.getTemperatureDisplayForUnitsPref(unitsPref, this)
//            ));
//
//            TextView highTempTV = findViewById(R.id.tv_favorite);
//            highTempTV.setText(getString(
//                    R.string.forecast_temp,
//                    forecastData.getHighTemp(),
//                    /* get correct temperature unit for unit preference setting */
//                    OpenWeatherUtils.getTemperatureDisplayForUnitsPref(unitsPref, this)
//            ));
//
//            TextView popTV = findViewById(R.id.tv_pop);
//            popTV.setText(getString(R.string.forecast_pop, forecastData.getPop()));
//
//            TextView cloudsTV = findViewById(R.id.tv_clouds);
//            cloudsTV.setText(getString(R.string.forecast_clouds, forecastData.getCloudCoverage()));
//
//            TextView windTV = findViewById(R.id.tv_wind);
//            windTV.setText(getString(
//                    R.string.forecast_wind,
//                    forecastData.getWindSpeed(),
//                    /* get correct wind speed unit for unit preference setting */
//                    OpenWeatherUtils.getWindSpeedDisplayForUnitsPref(unitsPref, this)
//            ));
//
//            ImageView windDirIV = findViewById(R.id.iv_wind_dir);
//            windDirIV.setRotation(forecastData.getWindDirDeg());
//
//            TextView forecastDescriptionTV = findViewById(R.id.tv_forecast_description);
//            forecastDescriptionTV.setText(forecastData.getShortDescription());
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (genreAdapter != null && mainRV != null){
            mainRV.setAdapter(genreAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_forecast_detail, menu);
        getMenuInflater().inflate(R.menu.activity_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.action_share:
            case R.id.action_view_movie_web:
                //shareForecastText();
                viewMovieWebsite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Link to movie imdb
    private void viewMovieWebsite(){
        //String movieID = "tt5109280";
        Uri movieWebUri =  Uri.parse("https://www.themoviedb.org/movie/" + movieData.getId());
        Intent intent = new Intent(Intent.ACTION_VIEW, movieWebUri);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            if(this.errorToast != null){
                this.errorToast.cancel();
            }
            this.errorToast = Toast.makeText(this, "Error", Toast.LENGTH_LONG);
            this.errorToast.show();
        }
    }

    /*
    * private void viewForecastCityInMap() {
        if (this.forecastCity != null) {
            Uri forecastCityGeoUri = Uri.parse(getString(
                    R.string.geo_uri,
                    this.forecastCity.getLatitude(),
                    this.forecastCity.getLongitude(),
                    12
            ));
            Intent intent = new Intent(Intent.ACTION_VIEW, forecastCityGeoUri);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                if (this.errorToast != null) {
                    this.errorToast.cancel();
                }
                this.errorToast = Toast.makeText(
                        this,
                        getString(R.string.action_map_error),
                        Toast.LENGTH_LONG
                );
                this.errorToast.show();
            }
        }
    }
    * */

    /**
     * This method uses an implicit intent to launch the Android Sharesheet to allow the user to
     * share the current forecast.
     */
//    private void shareForecastText() {
//        if (this.forecastData != null && this.forecastCity != null) {
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            Calendar date = OpenWeatherUtils.dateFromEpochAndTZOffset(
//                    forecastData.getEpoch(),
//                    forecastCity.getTimezoneOffsetSeconds()
//            );
//            String unitsPref = sharedPreferences.getString(
//                    getString(R.string.pref_units_key),
//                    getString(R.string.pref_units_default_value)
//            );
//            String shareText = getString(
//                    R.string.share_forecast_text,
//                    getString(R.string.app_name),
//                    this.forecastCity.getName(),
//                    getString(
//                            R.string.forecast_date_time,
//                            getString(R.string.forecast_date, date),
//                            getString(R.string.forecast_time, date)
//                    ),
//                    this.forecastData.getShortDescription(),
//                    getString(
//                            R.string.forecast_temp,
//                            forecastData.getHighTemp(),
//                            /* get correct temperature unit for unit preference setting */
//                            OpenWeatherUtils.getTemperatureDisplayForUnitsPref(unitsPref, this)
//                    ),
//                    getString(
//                            R.string.forecast_temp,
//                            forecastData.getLowTemp(),
//                            /* get correct temperature unit for unit preference setting */
//                            OpenWeatherUtils.getTemperatureDisplayForUnitsPref(unitsPref, this)
//                    ),
//                    getString(R.string.forecast_pop, this.forecastData.getPop())
//            );
//
//            Intent sendIntent = new Intent(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
//            sendIntent.setType("text/plain");
//
//            Intent chooserIntent = Intent.createChooser(sendIntent, null);
//            startActivity(chooserIntent);
//        }
//    }

}