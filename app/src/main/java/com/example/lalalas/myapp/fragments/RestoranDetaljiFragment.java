package com.example.lalalas.myapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.model.Restoran;


public class RestoranDetaljiFragment extends Fragment {



    public static final String NEKI_KEY_1 = "neki_key";
    FloatingActionButton btnReserve;
    private TextView txtTitle;
    private Restoran restoran;




    public RestoranDetaljiFragment() {
        // Required empty public constructor
    }


    public static RestoranDetaljiFragment newInstance(Restoran myRestoran) {

        RestoranDetaljiFragment fragment=new RestoranDetaljiFragment();
        Bundle args=new Bundle();
        args.putSerializable(NEKI_KEY_1,myRestoran);
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
            restoran = (Restoran) getArguments().getSerializable(NEKI_KEY_1);
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_restoran_detalji, container, false);
        final TextView resTitle=view.findViewById(R.id.resTitle);

        Restoran nRestoran=(Restoran)getArguments().getSerializable(NEKI_KEY_1);
       // resTitle.setText(nRestoran.getTitle()); puca aplikacija!!!
btnReserve=view.findViewById(R.id.btnIdiNaRezervacije);
btnReserve.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RezervacijaAddFragment.newInstance());
    }
});


        return view;

         };





    }








