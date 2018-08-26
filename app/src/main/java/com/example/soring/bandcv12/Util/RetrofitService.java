package com.example.soring.bandcv12.Util;

import com.example.soring.bandcv12.Model.Retrofit_BPM_Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("bpms")
    Call<Retrofit_BPM_Model> getdpmdata();
}
