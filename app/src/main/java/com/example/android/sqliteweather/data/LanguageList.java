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

public class LanguageList implements Serializable {
    private ArrayList<LanguageData> languages;

    public LanguageList() {
        this.languages = null;
    }

    public ArrayList<LanguageData> getGenresList() {
        return languages;
    }


    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<LanguageData> {
        @Override
        public LanguageData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject listObj = json.getAsJsonObject();

            return new LanguageData(
                    listObj.getAsJsonPrimitive("iso_639_1").getAsString(),
                    listObj.getAsJsonPrimitive("english_name").getAsString(),
                    listObj.getAsJsonPrimitive("name").getAsString()
            );
        }
    }
}
