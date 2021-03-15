package com.example.android.sqliteweather.data;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MovieData implements Serializable {
    private final static String POSTER_URL_FORMAT_STR = "https://image.tmdb.org/t/p/original";
    private static final String TAG = MovieData.class.getSimpleName();
    private ArrayList<Integer> genre_ids;
    private int id;
    private String original_Language;
    private String title;
    private String poster_path;
    private String overview;

    public MovieData(ArrayList<Integer> gi, int i, String ol, String t, String pp, String o){
        genre_ids = gi;
        id = i;
        original_Language = ol;
        title = t;
        poster_path = POSTER_URL_FORMAT_STR + pp;
        overview = o;

    }

    public ArrayList<Integer> getGenre_ids() { return genre_ids; }
    public int getId() { return id; }
    public String getOriginal_Language(){ return this.original_Language;}
    public String getTitle(){ return this.title;}
    public String getPoster_path(){return this.poster_path;}

    public String getOverview() { return overview; }

    public static String getPosterUrlFormatStr() {
        return POSTER_URL_FORMAT_STR;
    }
}
