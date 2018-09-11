package com.example.soring.bandcv12.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.soring.bandcv12.Model.Response_BPM;
import com.example.soring.bandcv12.Util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetService  extends Service{

    private boolean falg = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Log.e("asdasd","서비스 시작!");
        super.onCreate();
    }
        @Override
        public void onDestroy() {
        this.falg = true;
        super.onDestroy();
        }
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!falg){
                    try{
                        Call<Response_BPM> response = RetrofitClient.getInstance().getService().GetBPM();
                        response.enqueue(new Callback<Response_BPM>() {
                            @Override
                            public void onResponse(Call<Response_BPM> call, Response<Response_BPM> response) {
                                Log.e("onResponse BPM Called",""+response.body().getBpm());
                            }
                            @Override
                            public void onFailure(Call<Response_BPM> call, Throwable t) {
                                Log.e("onFailure Called",""+t.toString());
                            }
                        });
                        Thread.sleep(5000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Log.e("asdasd","들어오ㅑ");
            return super.onStartCommand(intent, flags, startId);
    }
}