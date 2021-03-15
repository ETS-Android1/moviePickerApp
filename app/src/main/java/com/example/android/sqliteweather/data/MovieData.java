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
    private static final String TAG = MovieData.class.getSimpleName();
    private ArrayList<Integer> genre_ids;
    private int id;
    private String original_Language;
    private String original_title;
    private String poster_path;
    private String overview;

    public MovieData(ArrayList<Integer> gi, int i, String ol, String ot, String pp, String o){
        genre_ids = gi;
        id = i;
        original_Language = ol;
        original_title = ot;
        poster_path = pp;
        overview = o;

    }

    public ArrayList<Integer> getGenre_ids() { return genre_ids; }
    public int getId() { return id; }
    public String getOriginal_Language(){ return this.original_Language;}
    public String getOriginal_title(){ return this.original_title;}
    public String getPoster_path(){return this.poster_path;}



}
