package com.serifgungor.firebasekullanimi.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.serifgungor.firebasekullanimi.R;

import java.util.Arrays;
import java.util.List;

public class GirisKayitActivity extends AppCompatActivity {

    int RC_SIGN_IN = 100;

    Button btn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giriskayit);


        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        String uid = sharedPreferences.getString("uid","");
        if(!uid.isEmpty()){
            //Kullanıcının UID bilgisi sharedPreferences'da tanımlıdır.
            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
        }


        btn = findViewById(R.id.btnGirisKayit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bir sonuç döndürmek üzere activity sayfasını açmak
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        startActivityForResult ile açılan ve işlem yapıldıktan sonra kapanan activity sayfasından dönen yanıtı yakalayabilmek için kullanılan metottur.
         */
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                editor.putString("email",user.getEmail());
                editor.putString("namesurname",user.getDisplayName());
                editor.putString("uid",user.getUid());
                editor.putString("phonenumber",user.getPhoneNumber());
                editor.putString("providerid",user.getProviderId());
                editor.commit();

                startActivity(new Intent(GirisKayitActivity.this,AnaActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Giriş başarısız", Toast.LENGTH_LONG).show();
            }
        }
    }
}
