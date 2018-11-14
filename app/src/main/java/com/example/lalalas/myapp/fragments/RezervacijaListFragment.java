package com.example.lalalas.myapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.AutentifikacijaResultVM;
import com.example.lalalas.myapp.model.RestoranPregledVM;
import com.example.lalalas.myapp.model.Rezervacija;
import com.example.lalalas.myapp.model.RezervacijaPregledVM;
import com.example.lalalas.myapp.model.Storage;

import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class RezervacijaListFragment extends Fragment {
    private ListView lvRezervacije;
    private FloatingActionButton btnNovaRezervacija;
    private BaseAdapter adapter;

    private RezervacijaPregledVM podaci;

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
        popuniRezervacijeTask();
        return view;
    }

    private void popuniRezervacijeTask() {
        AutentifikacijaResultVM x = MySession.getKorisnik();

        MyApiRequest.get(getActivity(),"Rezervacija/Index?id="+x.korisnikId, new MyRunnable<RezervacijaPregledVM>() {
            @Override
            public void run(RezervacijaPregledVM x) {
               podaci=x;
                popuniRezervacije();
            }
        });
    }
    private void popuniRezervacije() {


        adapter= new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.rows.size();
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
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.rezervacija_stavka, parent, false);
                }

                TextView txtFirstLine = view.findViewById(R.id.txtFirstLine_1);
                TextView txtSecondLine = view.findViewById(R.id.txtSecondLine_1);
                TextView txtThirdLine = view.findViewById(R.id.txtThirdLine_1);
                TextView txtMeta = view.findViewById(R.id.txtMeta_1);


                RezervacijaPregledVM.Row x = podaci.rows.get(i);

                txtFirstLine.setText(x.restoran);
                txtSecondLine.setText(x.vrsta + " za " + x.brojOsoba + " osoba");
                txtThirdLine.setText(x.vrijeme + "h");

                txtMeta.setText(x.datum);
                return view;
            }
        };

        lvRezervacije.setAdapter(adapter);

        lvRezervacije.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                RezervacijaPregledVM.Row x = podaci.rows.get(position);
                do_listViewLongClick(x);
                return true;
            }
        });
    }

    private void do_listViewLongClick(final RezervacijaPregledVM.Row x) {

        final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setMessage("Da li ste sigurni da želite obrisati rezervaciju?");

        adb.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                MyApiRequest.delete(getActivity(), "Rezervacija/Obrisi?id=" + x.id, new MyRunnable<Integer>(){

                    @Override
                    public void run(Integer o) {
                        podaci.rows.remove(x);
                        adapter.notifyDataSetChanged();
                    }
                });


            }
        });

        adb.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.show();
    }



}





