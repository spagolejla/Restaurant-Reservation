package com.example.lalalas.myapp.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGson {
    public static Gson build()
    {
        return builder().create();
    }
    public static GsonBuilder builder()
    {
        GsonBuilder builder = new GsonBuilder();

        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return builder;
    }
}
