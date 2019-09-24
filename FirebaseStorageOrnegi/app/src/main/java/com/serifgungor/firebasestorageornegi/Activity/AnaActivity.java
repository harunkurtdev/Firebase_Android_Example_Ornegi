package com.serifgungor.firebasestorageornegi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.serifgungor.firebasestorageornegi.Adapter.RecyclerViewAdapter;
import com.serifgungor.firebasestorageornegi.Model.Paylasim;
import com.serifgungor.firebasestorageornegi.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class AnaActivity extends AppCompatActivity {

    Button btnCikis, btnHesabiSil;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DatabaseReference mDatabase;


    EditText yazi;
    Button btnYaziPaylas, btnVeriSil;
  //  RecyclerView recyclerView;
    ArrayList<Paylasim> paylasimlar = new ArrayList<>();
  //  RecyclerViewAdapter adapter;

    public void veriEkle(Paylasim paylasim) {
        //FİREBASE VERİTABANINA VERİ EKLEME

        //11 haneli karmaşık değer üret
        String karakterler = "0123456789qwertyuiopasdfghjklzxcvbnm";
        String yeniDeger = "";
        Random random = new Random();
        for (int i = 0; i < 15; i++) {

            int rastgeleChr = random.nextInt(karakterler.length() - 1);
            yeniDeger += "" + karakterler.charAt(rastgeleChr);
        }

        String paylasimId = yeniDeger;
        paylasim.setPaylasimId(paylasimId);

        /*
        Paylasim p1 = new Paylasim();
        p1.setUid("1");
        p1.setTarih("");
        p1.setIcerik("içerik");
        mDatabase.child("paylasimlar").child(paylasimId).setValue(p1);
        */
        mDatabase.child("paylasimlar").child(paylasimId).setValue(paylasim);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        yazi = findViewById(R.id.etYazi);
        btnYaziPaylas = findViewById(R.id.btnYaziPaylas);
       // recyclerView = findViewById(R.id.recyclerViewYazilar);
        btnVeriSil = findViewById(R.id.btnVeriSil);

        mDatabase = FirebaseDatabase.getInstance().getReference();

//        adapter = new RecyclerViewAdapter(getApplicationContext(), paylasimlar, new RecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(final Paylasim paylasim) {
//
//
//                String uid = sharedPreferences.getString("uid", "");
//
//                if (paylasim.getUid().equals(uid)) {
//                    //Paylaşım bana ait ise
//
//
//                    AlertDialog.Builder adb = new AlertDialog.Builder(AnaActivity.this);
//                    adb.setTitle("Veri silinsin mi ?");
//                    adb.setMessage("Veritabanından veriyi silmek istiyor musunuz ?");
//                    adb.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    adb.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            //Firebase Database'den child name'i bildiğimiz bir veriyi silmek.
//                            mDatabase.child("paylasimlar").child(paylasim.getPaylasimId()).removeValue(new DatabaseReference.CompletionListener() {
//                                @Override
//                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                                    Toast.makeText(getApplicationContext(), "Veri silindi", Toast.LENGTH_LONG).show();
//                                }
//                            });
//
//
//                        }
//                    });
//                    //adb.create();
//                    adb.show();
//
//                } else {
//                    //Paylaşım size ait değildir
//                    Toast.makeText(getApplicationContext(),"Paylaşım size ait değildir",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
     //   recyclerView.setAdapter(adapter);


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);


        mDatabase.child("paylasimlar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*
                 Veritabanına veri ekleme,silme,güncellenme zamanında çalışacak mettottur.
                 */
                paylasimlar.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Paylasim paylasim = snapshot.getValue(Paylasim.class);
                    //Firebase database'den gelen değeri Object'e çevirdik.
                    paylasimlar.add(paylasim);
                }
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Veritabanı bağlantısı hatasını bulabildiğimiz kısım.
            }
        });


        btnVeriSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnYaziPaylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paylasimlar.clear();

                Paylasim paylasim = new Paylasim();
                paylasim.setIcerik(yazi.getText().toString());
                paylasim.setTarih(new Date().toString());
                paylasim.setUid(sharedPreferences.getString("uid", ""));
                veriEkle(paylasim);

                //Arraylist'deki değişikliği adapter'a ilet.
               // adapter.notifyDataSetChanged();
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
