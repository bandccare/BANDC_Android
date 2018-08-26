package com.example.soring.bandcv12.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient mInstance = new RetrofitClient();
    public static RetrofitClient getInstance(){
        return mInstance;
    }

    private RetrofitClient(){};

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.30.1.14:5000/").addConverterFactory(GsonConverterFactory.create()).build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService(){
        return service;
    }
}
