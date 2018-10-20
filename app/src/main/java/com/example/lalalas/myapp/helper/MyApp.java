package com.example.lalalas.myapp.helper;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Adil on 11.5.2015.
 *
 * obavezno navesti app name u android-manifest.xml
 *
 *  <application
 android:allowBackup="true"
 android:name=".MyApp"
 *
 */
public class MyApp extends Application {

    private static WeakReference<Context> context;

    public static Context getContext() {
        return context.get();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(getApplicationContext());
    }


}
