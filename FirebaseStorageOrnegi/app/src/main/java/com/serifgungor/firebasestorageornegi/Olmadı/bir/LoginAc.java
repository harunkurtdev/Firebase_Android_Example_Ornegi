package com.serifgungor.firebasestorageornegi.Olmadı.bir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.serifgungor.firebasestorageornegi.R;

import java.util.HashMap;
import java.util.Map;

public class LoginAc extends AppCompatActivity {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button giris = (Button) findViewById(R.id.giris);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(
                        email.getText().toString(),
                        password.getText().toString())
                        .addOnCompleteListener(LoginAc.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplication(), Profil.class));
                                } else {
                                    Log.e("giris hatası", task.getException().toString());
                                }
                            }
                        });
            }
        });
        final Button kayit = (Button) findViewById(R.id.kayit);
        kayit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String emailAddr = email.getText().toString();
                String pass = password.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(emailAddr, pass)
                        .addOnCompleteListener(LoginAc.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    kullaniciOlustur();
                                    kullaniciGuncelle();
                                } else {
                                    Log.e("Yeni Kullanıcı Hatası", task.getException().getMessage());
                                }

                            }
                        });
            }
        });
    }

    private void kullaniciOlustur() {
        Map<String, String> yeniUser = new HashMap<String, String>();
        yeniUser.put("ad", "Adınız");
        yeniUser.put("soyad", "Soyadınız");

        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("users")
                .child(firebaseAuth.getCurrentUser().getUid())
                .setValue(yeniUser);
    }

    private void kullaniciGuncelle() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName("Takma Adınız")
                .setPhotoUri(null)
                .build();

        firebaseUser.updateProfile(userProfileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.e("Güncelleme Hatası", task.getException().getMessage());
                        }
                        startActivity(new Intent(LoginAc.this, Profil.class));
                    }
                });
    }
}

