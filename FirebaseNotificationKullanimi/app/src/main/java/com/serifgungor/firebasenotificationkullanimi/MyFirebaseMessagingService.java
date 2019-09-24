package com.serifgungor.firebasenotificationkullanimi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /*
            Bildirimin yakalandığı metottur.
         */
        Log.d("MESAJ","FROM:"+remoteMessage.getFrom());
        if(remoteMessage.getData().size() > 0){
            Log.d("MESAJ","Data: "+remoteMessage.getData());
            String website = remoteMessage.getData().get("website");
            if(!website.isEmpty()){
                Log.d("WEB",website);
            }
        }
        if(remoteMessage.getNotification() != null){
            Log.d("BİLDİRİM_İCERİĞİ",remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d("", "Refreshed token: " + token);
    }

}
