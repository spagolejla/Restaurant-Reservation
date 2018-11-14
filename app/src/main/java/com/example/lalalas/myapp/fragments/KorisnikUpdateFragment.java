package com.example.lalalas.myapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.model.KorisnikPregledVM;

public class KorisnikUpdateFragment extends Fragment {

    private static final String KORISNIK_KEY = "korisnik_key";
    private KorisnikPregledVM.Row MyKorisnik;

    private EditText txtOldPass;
    private EditText txtNewPass;
    private Button btnSave;


    public KorisnikUpdateFragment() {
        // Required empty public constructor
    }


    public static KorisnikUpdateFragment newInstance(KorisnikPregledVM.Row korisnik) {
        KorisnikUpdateFragment fragment = new KorisnikUpdateFragment();
        Bundle args = new Bundle();
        args.putSerializable(KORISNIK_KEY,korisnik);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           MyKorisnik= (KorisnikPregledVM.Row) getArguments().getSerializable(KORISNIK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_korisnik_update, container, false);

        txtOldPass=view.findViewById(R.id.txtOldPass);
        txtNewPass=view.findViewById(R.id.txtNewPass);
        btnSave=view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnSaveClick();
            }
        });

        return view;
    }

    private void do_btnSaveClick() {

        if (txtOldPass.getText().toString().equals(MyKorisnik.password)) {

            if (txtNewPass.getText().toString().length()==0)
            {
                Toast.makeText(getActivity(),"Niste unijeli novi password!",Toast.LENGTH_LONG).show();

            }
            else {
                MyKorisnik.password = txtNewPass.getText().toString();

                MyApiRequest.put(getActivity(), "Korisnik/Update?id="+MyKorisnik.korisnickiNalogId, MyKorisnik, null);

                MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment, KorisnickiProfilFragment.newInstance());

                Toast.makeText(getActivity(), "Uspješno ste promjenili password!", Toast.LENGTH_LONG).show();
            }
        }
        else
        {

            Toast.makeText(getActivity(),"Greška! Pogrešan password!",Toast.LENGTH_LONG).show();

        }
    }

}



