package com.example.soring.bandcv12.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient mInstance = new RetrofitClient();
    public static RetrofitClient getInstance(){
        return mInstance;
    }

    private RetrofitClient(){}

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.3:4000/").addConverterFactory(GsonConverterFactory.create()).build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    Retrofit retrofit2 = new Retrofit.Builder().baseUrl("http://192.168.0.3:5000/").addConverterFactory(GsonConverterFactory.create()).build();

    RetrofitService service2 = retrofit2.create(RetrofitService.class);

    public RetrofitService getService(){
        return service;
    }

    public RetrofitService getService2() {
        return service2;
    }
}
