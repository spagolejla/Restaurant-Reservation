package com.example.lalalas.myapp.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.model.KorisnikAddVM;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtIme;
    private EditText txtPrezime;
    private EditText txtMail;

    private EditText txtPassword;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        txtIme=findViewById(R.id.txtImeKorisnik);
        txtPrezime=findViewById(R.id.txtPrezimeKorisnik);
        txtMail=findViewById(R.id.txtMailKorisnik);

        txtPassword=findViewById(R.id.txtPasswordKorisnik);
        btnReg=findViewById(R.id.btnReg);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnRegClick();
            }
        });


    }

    private void do_btnRegClick() {

        String ime=txtIme.getText().toString();
        String prezime=txtPrezime.getText().toString();
        String email=txtMail.getText().toString();
        String password=txtPassword.getText().toString();

        if (ime == null  || ime.isEmpty() || ime.equals("null") || ime.length()==0 ){

            Toast.makeText(this,"Niste popunili ime!",Toast.LENGTH_LONG).show();

            }

         else if(prezime == null  || prezime.isEmpty() || prezime.equals("null")|| prezime.length()==0)
        {

            Toast.makeText(this,"Niste popunili prezime!",Toast.LENGTH_LONG).show();

        }
        else if(email == null  || email.isEmpty() || email.equals("null") || prezime.length()==0)
        {

            Toast.makeText(this,"Niste popunili mail!",Toast.LENGTH_LONG).show();

        }
        else if(password == null  || password.equals("null") || password.isEmpty() || password.length()==0)
        {

            Toast.makeText(this,"Niste unijeli password",Toast.LENGTH_LONG).show();


        }
        else{
            try {
                KorisnikAddVM newUser = new KorisnikAddVM();

                newUser.ime = txtIme.getText().toString();
                newUser.prezime = txtPrezime.getText().toString();
                newUser.email = txtMail.getText().toString();
                newUser.username = txtMail.getText().toString();
                newUser.password = txtPassword.getText().toString();
                MyApiRequest.post(this, "Korisnik/AddKorisnik", newUser, null);
                Toast.makeText(this,"Registracija je uspjesno prošla! Mozete se prijaviti!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);

            }catch (Exception e)
            {
                Toast.makeText(this,"Greška! Registracija nije uspjesno prošla!",Toast.LENGTH_LONG).show();
            }



        }





    }
}
