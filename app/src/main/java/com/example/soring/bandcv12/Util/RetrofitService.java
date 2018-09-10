package com.example.soring.bandcv12.Util;

import com.example.soring.bandcv12.Model.Request_Oauth;
import com.example.soring.bandcv12.Model.Response_Oauth;
import com.example.soring.bandcv12.Model.Retrofit_BPM_Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @GET("bpms")
    Call<Retrofit_BPM_Model> getdpmdata();

    @POST("token")
    Call<Response_Oauth> GetOauthToken(@Body Request_Oauth request_oauth);

}
