package com.example.lalalas.myapp.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.Storage;

public class GlavniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Korisnik x = MySession.getKorisnik(); ne radi
Korisnik x= Storage.getKorisnici().get(0);
        if (x==null)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, MainActivity.class));

    }
}
