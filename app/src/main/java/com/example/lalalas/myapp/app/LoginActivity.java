package com.example.lalalas.myapp.app;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.AutentifikacijaLoginPostVM;
import com.example.lalalas.myapp.model.AutentifikacijaResultVM;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.KorisnikPregledVM;
import com.example.lalalas.myapp.model.Storage;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private TextView txtGoToReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtGoToReg=findViewById(R.id.goToReg);

        txtGoToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLoginClick();
            }
        });
    }

    private void do_btnLoginClick() {

        String strUsername = txtUsername.getText().toString();
        String strPassword = txtPassword.getText().toString();
        String deviceInfo = android.os.Build.DEVICE+" | " +  android.os.Build.VERSION.RELEASE + " | " + android.os.Build.PRODUCT + " | " + Build.MODEL;


        AutentifikacijaLoginPostVM model = new AutentifikacijaLoginPostVM(strUsername, strPassword, deviceInfo);

        MyApiRequest.post(this, "Autentifikacija/LoginCheck", model, new MyRunnable<AutentifikacijaResultVM>() {
            @Override
            public void run(AutentifikacijaResultVM x) {
                checkLogin(x);
            }
        });
    }

    private void checkLogin(AutentifikacijaResultVM x) {
        if (x==null)
        {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan username/password", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            MySession.setKorisnik( x);
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}



