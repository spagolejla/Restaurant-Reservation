package com.example.lalalas.myapp.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.AutentifikacijaResultVM;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.KorisnikPregledVM;
import com.example.lalalas.myapp.model.RestoranPregledVM;

public class GlavniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutentifikacijaResultVM x = MySession.getKorisnik();


        if (x==null)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, MainActivity.class));

    }
}
