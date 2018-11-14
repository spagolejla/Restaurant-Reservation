package com.example.lalalas.myapp.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.AutentifikacijaResultVM;
import com.example.lalalas.myapp.model.KorisnikPregledVM;
import com.example.lalalas.myapp.model.Restoran;
import com.example.lalalas.myapp.model.RestoranPregledVM;
import com.example.lalalas.myapp.model.Rezervacija;
import com.example.lalalas.myapp.model.RezervacijaAddVM;
import com.example.lalalas.myapp.model.Storage;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private RestoranPregledVM Restorani;
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
               // txtDatum.setText(mDay+"."+(mMonth+1)+"."+mYear); //yyyyy-mm-dd
                txtDatum.setText(mYear+"-"+(mMonth+1)+"-"+mDay);
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

        popuniPodatkeTask();

        return view;
    }


    private void popuniPodatkeTask() {
        MyApiRequest.get(getActivity(),"Restoran/Index", new MyRunnable<RestoranPregledVM>() {
            @Override
            public void run(RestoranPregledVM x) {
                popuniPodatke (x);
            }
        });




    }

    private void popuniPodatke(RestoranPregledVM x) {
        Restorani=x;
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getRestoraniString(x));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRestoran.setAdapter(dataAdapter);
    }

    private List<String> getRestoraniString(RestoranPregledVM restorani)
    {

        List<String> result = new ArrayList<>();

        for (RestoranPregledVM.Row x : restorani.rows) {
            result.add(x.naziv + " " + x.grad);
        }

        return result;
    }

    private void do_btnDodaj() {


        AutentifikacijaResultVM prijavljeniKorisnik = MySession.getKorisnik();
        SpremiRezervaciju();




    }

    private void SpremiRezervaciju()  {


        int position = spinnerRestoran.getSelectedItemPosition();
        RestoranPregledVM.Row restoran = Restorani.rows.get(position);
AutentifikacijaResultVM kor=MySession.getKorisnik();




        RezervacijaAddVM novaRezervacija= new RezervacijaAddVM();


try {

    novaRezervacija.vrsta=txtVrstaRezervacije.getText().toString();
    novaRezervacija.datum= txtDatum.getText().toString();
    novaRezervacija.vrijeme= txtVrijeme.getText().toString();
    novaRezervacija.korsnikId=kor.korisnikId ;
    novaRezervacija.restoranId=restoran.id;
    novaRezervacija.brojOsoba=Integer.parseInt(txtBrojOsoba.getText().toString());


    MyApiRequest.post(getActivity(), "Rezervacija/Add", novaRezervacija, null);
    Toast.makeText(getActivity(), "Uspješno ste izvršili rezervaciju!", Toast.LENGTH_LONG).show();

    MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RezervacijaListFragment.newInstance());
}catch (Exception e){
    Toast.makeText(getActivity(), "Greška! Sva polja moraju biti popunjena!", Toast.LENGTH_LONG).show();

}



    }


}
