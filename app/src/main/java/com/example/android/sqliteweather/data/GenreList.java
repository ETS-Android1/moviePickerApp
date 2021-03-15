package com.example.android.sqliteweather.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GenreList implements Serializable {
    @SerializedName("genres")
    private ArrayList<GenreData> genres;

    public GenreList() {
        this.genres = null;
    }

    public ArrayList<GenreData> getGenresList() {
        return genres;
    }
}
