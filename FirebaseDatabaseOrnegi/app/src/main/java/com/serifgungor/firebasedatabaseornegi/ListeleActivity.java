package com.serifgungor.firebasedatabaseornegi;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListeleActivity extends AppCompatActivity {
    Button btnUyeleriListele;
    GridView gridView;
    ArrayList<User> uyeler;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listele);

        uyeler = new ArrayList<>();
        btnUyeleriListele = (Button) findViewById(R.id.btnUyeleriListele);
        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridAdapter(this, uyeler);

        btnUyeleriListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setAdapter(gridAdapter);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    uyeler.add(
                            new User(
                                    user.getEmail(),
                                    user.getSifre(),
                                    user.getAd(),
                                    user.getSoyad()
                            )
                    );
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });

    }
}