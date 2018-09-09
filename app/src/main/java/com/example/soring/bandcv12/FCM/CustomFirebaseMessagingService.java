package com.example.soring.bandcv12.FCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.soring.bandcv12.MainActivity;
import com.example.soring.bandcv12.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class CustomFirebaseMessagingService  extends FirebaseMessagingService {
   // RetroClient2 retroClient2;
    String title;
    String body;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        retroClient2 = RetroClient2.getInstance().createBaseApi2();
//
        Map<String, String> pushDataMap = remoteMessage.getData();
//        Log.e("fcm get data",""+pushDataMap.get("detect"));
//
//        if(pushDataMap.get("check").equals("heart")){
//            HashMap<String, Object> alarmmap = new HashMap<>();
//            alarmmap.put("alarm","alarm");
//            retroClient2.Send_Alarm(alarmmap, new RetroCallback() {
//                @Override
//                public void onError(Throwable t) {
//                }
//                @Override
//                public void onSuccess(int code, Object receivedData) {
//                    Log.e("send alarm","messagingservice");
//                }
//
//                @Override
//                public void onFailure(int code) {
//                }
//            });
//        }

        sendNotification(pushDataMap);
    }

    private void sendNotification(Map<String, String> dataMap) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(dataMap != null){
            if(dataMap.get("check").equals("notouch")){
                title = "BandCCare";
                body = "밴드 미착용 감지";
            }

            if(dataMap.get("check").equals("heart") && dataMap.get("detect").equals("false")){
                title = "심박수 이상 감지";
                body = "움직임이 감지되지 않았습니다.";
            }
        }
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this,"0")
                .setSmallIcon(R.mipmap.notification)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{1000, 1000})
                .setLights(Color.WHITE, 1500, 1500)
                .setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(1 /* ID of notification */, nBuilder.build());
    }
}
