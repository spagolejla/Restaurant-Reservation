package com.example.lalalas.myapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.AutentifikacijaResultVM;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.KorisnikAddVM;
import com.example.lalalas.myapp.model.KorisnikPregledVM;


public class KorisnickiProfilFragment extends Fragment {
//dodati kasnije da se moze promjeniti password
    private TextView txtKorisnik;
    private TextView txtUsername;
    private TextView txtPassword;
    private TextView txtMail;
   private Button btnChanePass;



    AutentifikacijaResultVM x = MySession.getKorisnik();


    public KorisnickiProfilFragment() {
        // Required empty public constructor
    }


    public static KorisnickiProfilFragment newInstance() {

        KorisnickiProfilFragment fragment=new KorisnickiProfilFragment();
        Bundle args=new Bundle();

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

        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_korisnicki_profil, container, false);
        txtKorisnik=view.findViewById(R.id.txtImePrezime);
        txtUsername=view.findViewById(R.id.txtUsername);
        txtPassword=view.findViewById(R.id.txtPassword);
        txtMail=view.findViewById(R.id.txtEmail);
        btnChanePass=view.findViewById(R.id.btnChangePassword);




        txtKorisnik.setText(x.ime+" "+x.prezime);
        txtUsername.setText(x.username);
        txtPassword.setText("*****");
        txtMail.setText(x.mail);

        final KorisnikPregledVM.Row korisnik=new KorisnikPregledVM.Row();
          korisnik.korisnickiNalogId=x.korisnickiNalogId;  korisnik.ime=x.ime; korisnik.prezime=x.prezime;korisnik.password=x.password;korisnik.email=x.mail;

        btnChanePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,KorisnikUpdateFragment.newInstance(korisnik));
            }
        });

        return view;

         };





    }








