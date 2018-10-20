package com.example.lalalas.myapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.Restoran;


public class KorisnickiProfilFragment extends Fragment {



    public static final String NEKI_KEY = "neki_key";

    private MyRunnable<Korisnik> callback;




    public KorisnickiProfilFragment() {
        // Required empty public constructor
    }


    public static KorisnickiProfilFragment newInstance(MyRunnable myCallback) {

        KorisnickiProfilFragment fragment=new KorisnickiProfilFragment();
        Bundle args=new Bundle();
        args.putSerializable(NEKI_KEY,myCallback);
        fragment.setArguments(args);
       // args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
       // fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            callback = (MyRunnable<Korisnik>) getArguments().getSerializable(NEKI_KEY);
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_korisnicki_profil, container, false);


        return view;

         };





    }








