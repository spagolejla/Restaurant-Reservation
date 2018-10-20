package com.example.lalalas.myapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lalalas.myapp.R;


public class BlankFragment extends android.app.Fragment {




    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance() {

        return new BlankFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }







}
