package com.example.lalalas.myapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.lalalas.myapp.app.LoginActivity;


import com.example.lalalas.myapp.model.Korisnik;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MySession {
    private static final String PREFS_NAME = "DatotekaZaSharedPrefernces";
    private static String nekikey="Key_korisnik";

    public static Korisnik getKorisnik()
    {
        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String strJson = sharedPreferences.getString(nekikey, "");
        if (strJson.length() == 0)
            return null;

        Gson gson=new GsonBuilder().create();
        Korisnik x = gson.fromJson(strJson, Korisnik.class);
        return x;
    }

    public static void setKorisnik(Context context,Korisnik x)
    {


        Gson gson=new GsonBuilder().create();
        String strJson=gson.toJson(x);



        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nekikey, strJson);
        editor.apply();
    }
}
