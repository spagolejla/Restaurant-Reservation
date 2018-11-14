package com.example.lalalas.myapp.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.model.Restoran;
import com.example.lalalas.myapp.model.RestoranPregledVM;


public class RestoranDetaljiFragment extends Fragment {



    public static final String RESTORAN_KEY = "restoran_key";
    private RestoranPregledVM.Row restoran;

    FloatingActionButton btnReserve;
    private TextView txtTitle;
    private TextView txtDescription;
    private TextView txtAdresa;
    private TextView txtTelefon;
    private TextView txtMail;






    public RestoranDetaljiFragment() {
        // Required empty public constructor
    }


    public static RestoranDetaljiFragment newInstance(RestoranPregledVM.Row myRestoran) {

        RestoranDetaljiFragment fragment=new RestoranDetaljiFragment();
        Bundle args=new Bundle();
        args.putSerializable(RESTORAN_KEY,myRestoran);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restoran = (RestoranPregledVM.Row) getArguments().getSerializable(RESTORAN_KEY);
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_restoran_detalji, container, false);
        txtTitle=view.findViewById(R.id.resTitle);
        txtDescription=view.findViewById(R.id.resDescription);
        txtAdresa=view.findViewById(R.id.resAdresa);
        txtTelefon=view.findViewById(R.id.resTelefon);
        txtMail=view.findViewById(R.id.resMail);


        txtTitle.setText(restoran.naziv + " "+restoran.grad);
        txtDescription.setText(restoran.opis);
        txtAdresa.setText(restoran.adresa);
        txtTelefon.setText(restoran.telefon);
        txtMail.setText(restoran.mail);



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








