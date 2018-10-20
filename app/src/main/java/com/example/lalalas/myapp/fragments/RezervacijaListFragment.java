package com.example.lalalas.myapp.fragments;

import android.app.Dialog;
import android.content.Context;
import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.Restoran;
import com.example.lalalas.myapp.model.Rezervacija;
import com.example.lalalas.myapp.model.Storage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class RezervacijaListFragment extends Fragment {
    private ListView lvRezervacije;
    private FloatingActionButton btnNovaRezervacija;

    private Rezervacija rezervacija=new Rezervacija();

    public static RezervacijaListFragment newInstance() {

        //   Bundle args = new Bundle();

        // RestoranListFragment fragment = new RestoranListFragment();
        //fragment.setArguments(args);
        //return fragment;

        return new RezervacijaListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rezervacija_list, container, false);
        lvRezervacije = (ListView) view.findViewById(R.id.lvRezervacije);
       btnNovaRezervacija=(FloatingActionButton) view.findViewById(R.id.fab);
        btnNovaRezervacija.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RezervacijaAddFragment.newInstance());
            }
        });
popuniRezervacije();
        return view;
    }

    private void popuniRezervacije() {
        final List<Rezervacija> podaci=Storage.getRezervacije();

        lvRezervacije.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {
                if (view==null)
                {
                    LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.rezervacija_stavka,parent,false);
                }

                TextView txtFirstLine=view.findViewById(R.id.txtFirstLine_1);
                TextView txtSecondLine=view.findViewById(R.id.txtSecondLine_1);
                TextView txtThirdLine=view.findViewById(R.id.txtThirdLine_1);
                TextView txtMeta=view.findViewById(R.id.txtMeta_1);


                Rezervacija x= podaci.get(i);

               txtFirstLine.setText(x.getRestoran().getTitle());
               txtSecondLine.setText(x.getVrstaRezervacije()+" za "+x.getBrojOsoba()+ " osoba");
txtThirdLine.setText(x.getVrijeme()+"h");

txtMeta.setText(x.getDatum());

                return view;
            }  });

    }


}





