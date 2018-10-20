package com.example.lalalas.myapp.app;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MySession;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.Storage;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

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

        Korisnik x = Storage.LoginCheck(strUsername, strPassword);

        if (x==null)
        {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan usernam/password", Snackbar.LENGTH_LONG).show();
        }
        else
        {
           // MySession.setKorisnik(this,x);
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}

