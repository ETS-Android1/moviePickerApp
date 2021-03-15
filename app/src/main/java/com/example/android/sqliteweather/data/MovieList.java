package com.example.android.sqliteweather.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MovieList implements Serializable{
    private ArrayList<MovieData> movieList;

    public MovieList(ArrayList<MovieData> movLis){
        this.movieList = movLis;
    }

    public ArrayList<MovieData> getMovieList() { return movieList; }

    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<ArrayList<MovieData>> {
        @Override
        public ArrayList<MovieData> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ArrayList<MovieData> movieList = new ArrayList<>();

            JsonObject listObj = json.getAsJsonObject();
            JsonArray resultsArr = listObj.getAsJsonArray("results");
            for (int i = 0; i < resultsArr.size(); i++) {
                ArrayList<Integer> idsSto = new ArrayList<>();
                JsonObject movieObj = resultsArr.get(i).getAsJsonObject();
                JsonArray genre_ids = movieObj.getAsJsonArray("genre_ids");
                for (int j = 0; j < genre_ids.size(); j++) {
                    idsSto.add(genre_ids.get(j).getAsInt());
                }


                movieList.add(new MovieData(idsSto,
                        movieObj.getAsJsonPrimitive("id").getAsInt(),
                        movieObj.getAsJsonPrimitive("original_language").getAsString(),
                        movieObj.getAsJsonPrimitive("original_title").getAsString(),
                        movieObj.getAsJsonPrimitive("poster_path").getAsString()
                ));
            }


            return movieList;
        }
    }

}
