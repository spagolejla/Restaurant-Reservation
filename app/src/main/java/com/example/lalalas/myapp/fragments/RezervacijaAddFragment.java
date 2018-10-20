package com.example.lalalas.myapp.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.model.Restoran;
import com.example.lalalas.myapp.model.Rezervacija;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.model.Storage;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RezervacijaAddFragment extends Fragment  {
    private static final String ARG_PARAM1 = "param1";
    private MyRunnable<Rezervacija> pMyCallBack;
    private Spinner spinnerRestoran;
    private EditText txtVrstaRezervacije;
    private EditText txtDatum;
    private EditText txtVrijeme;

    private EditText txtBrojOsoba;
    private List<Restoran> restorani;
    private AppCompatImageButton btnDatePicker;
    private AppCompatImageButton btnTimePicker;

    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    public  RezervacijaAddFragment () {


    }

    public static RezervacijaAddFragment newInstance() {

        RezervacijaAddFragment fragment = new RezervacijaAddFragment();

        return fragment;

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rezervacija_add, container, false);



        txtVrstaRezervacije = view.findViewById(R.id.txtVrstaRezervacije);
        txtBrojOsoba = view.findViewById(R.id.txtBrojOsoba);
        txtDatum=view.findViewById(R.id.txtDatum);
        txtVrijeme=view.findViewById(R.id.txtVrijeme);
        btnTimePicker=view.findViewById(R.id.btnTimePicker);
        btnDatePicker=view.findViewById(R.id.btnDatePicker);
btnDatePicker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        c=Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);

        dpd=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                txtDatum.setText(mDay+"."+(mMonth+1)+"."+mYear);
            }
        },day,month,year);
        dpd.show();
    }
});

btnTimePicker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        c=Calendar.getInstance();
        int h=c.get(Calendar.HOUR_OF_DAY);
        int m=c.get(Calendar.MINUTE);

        tpd=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                txtVrijeme.setText(h+":"+m);
            }
        },h,m,false);
        tpd.show();
    }
});

        spinnerRestoran = view.findViewById(R.id.spinnerRestoran);
        view.findViewById(R.id.btnSnimi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnDodaj();
            }
        });

        popuniPodatke();

        return view;
    }

    private List<String> getRestoraniString()
    {
        List<String> result = new ArrayList<>();

        for (Restoran x : restorani) {
            result.add(x.getTitle() + " " + x.getCity());
        }

        return result;
    }

    private void popuniPodatke() {
        restorani = Storage.getRestorani();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getRestoraniString());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRestoran.setAdapter(dataAdapter);


    }

    private void do_btnDodaj() {
        int position = spinnerRestoran.getSelectedItemPosition();
        Restoran x = restorani.get(position);

    //    Rezervacija rezervacija = new Rezervacija(txtVrstaRezervacije.getText().toString(),Storage.getKorisnici().get(0),x, 7);

        Rezervacija rezervacija=new Rezervacija(txtVrstaRezervacije.getText().toString(),Storage.getKorisnici().get(0),x,Integer.parseInt(txtBrojOsoba.getText().toString()),txtDatum.getText().toString(),txtVrijeme.getText().toString());
        Storage.addRezervacija(rezervacija);

        MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RezervacijaListFragment.newInstance());


    }


}
