package com.example.soring.bandcv12.Util;

import com.example.soring.bandcv12.Model.Request_FCM_Token;
import com.example.soring.bandcv12.Model.Request_Oauth;
import com.example.soring.bandcv12.Model.Response_BPM;
import com.example.soring.bandcv12.Model.Response_Check;
import com.example.soring.bandcv12.Model.Response_Oauth;
import com.example.soring.bandcv12.Model.Retrofit_BPM_Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("bpms")
    Call<Retrofit_BPM_Model> getdpmdata();

    @POST("token")
    Call<Response_Oauth> GetOauthToken(@Body Request_Oauth request_oauth);

    @GET("bpm")
    Call<Response_BPM> GetBPM();

    @POST("fcmtoken")
    Call<Response_Check> Send_FCM_Token(@Body Request_FCM_Token request_fcm_token);

    @GET("gettoken")
    Call<Response_Check> Send_User_Id(@Query("user_id") String user_id);

}
