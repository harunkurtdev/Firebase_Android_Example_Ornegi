package com.serifgungor.firebasedatabaseornegi;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KayitActivity extends AppCompatActivity {
    EditText etEmail,etSifre,etAd,etSoyad;
    Button btnKayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        btnKayit = (Button)findViewById(R.id.btnUyeKaydet);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etSifre = (EditText)findViewById(R.id.etPassword);
        etAd = (EditText)findViewById(R.id.etAd);
        etSoyad = (EditText)findViewById(R.id.etSoyad);

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users");
                dbRef.push().setValue(
                        new User(
                                etEmail.getText().toString(),
                                etSifre.getText().toString(),
                                etAd.getText().toString(),
                                etSoyad.getText().toString()
                        )
                );
                finish();
            }
        });
    }

}