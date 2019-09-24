package com.serifgungor.firebasekullanimi.Activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.serifgungor.firebasekullanimi.Adapter.RecyclerViewAdapter;
import com.serifgungor.firebasekullanimi.Model.Paylasim;
import com.serifgungor.firebasekullanimi.R;

import java.util.ArrayList;
import java.util.Date;

public class AnaActivity extends AppCompatActivity {

    Button btnCikis, btnHesabiSil;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DatabaseReference mDatabase;


    EditText yazi;
    Button btnYaziPaylas;
    RecyclerView recyclerView;
    ArrayList<Paylasim> paylasimlar = new ArrayList<>();
    RecyclerViewAdapter adapter;

    public void doldur() {
        paylasimlar.add(new Paylasim("İÇERİK", "UİD", "TARİH"));
    }

    public void veriEkle(Paylasim paylasim){
        //FİREBASE VERİTABANINA VERİ EKLEME
        String userId = "1";
         mDatabase.child("paylasimlar").child(userId).setValue(paylasim);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        yazi = findViewById(R.id.etYazi);
        btnYaziPaylas = findViewById(R.id.btnYaziPaylas);
        recyclerView = findViewById(R.id.recyclerViewYazilar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        doldur();

        adapter = new RecyclerViewAdapter(getApplicationContext(),paylasimlar);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        btnYaziPaylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paylasim paylasim = new Paylasim();
                paylasim.setIcerik(yazi.getText().toString());
                paylasim.setTarih(new Date().toString());
                paylasim.setUid(sharedPreferences.getString("uid",""));
                veriEkle(paylasim);
            }
        });


        btnCikis = findViewById(R.id.btnCikis);
        btnHesabiSil = findViewById(R.id.btnKayitSil);



        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                editor.clear();
                                editor.commit();
                                finish(); // Activity sayfasını kapat
                            }
                        });
            }
        });

        btnHesabiSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .delete(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                editor.clear();
                                editor.commit();
                                finish(); //Activity sayfasını kapat
                            }
                        });
            }
        });
    }
}
