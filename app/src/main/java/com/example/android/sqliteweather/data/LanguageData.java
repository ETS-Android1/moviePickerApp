package com.example.android.sqliteweather.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.Serializable;
import java.lang.reflect.Type;

public class LanguageData implements Serializable {
    private String iso;
    private String english_name;
    private String name;

    public LanguageData(String i, String en, String n){
        this.iso = i;
        this.english_name = en;
        this.name = n;
    }


}
