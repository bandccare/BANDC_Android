package com.example.soring.bandcv12.Util;

import com.example.soring.bandcv12.Model.Request_FCM_Token;
import com.example.soring.bandcv12.Model.Request_Motor;
import com.example.soring.bandcv12.Model.Request_alarm;
import com.example.soring.bandcv12.Model.Request_exit;
import com.example.soring.bandcv12.Model.Response_BPM;
import com.example.soring.bandcv12.Model.Response_Check;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("bpm")
    Call<Response_BPM> GetBPM(@Query("check") String check);

    @POST("fcmtoken")
    Call<Response_Check> Send_FCM_Token(@Body Request_FCM_Token request_fcm_token);

    @GET("gettoken")
    Call<Response_Check> Send_User_Id(@Query("user_id") String user_id);

    @POST("post")
    Call<Request_Motor> Motor_Controller(@Body Request_Motor request_motor);

    @POST("exit")
    Call<Response_Check> Send_exit(@Body Request_exit request_exit);

    @POST("alarm")
    Call<Response_Check> Send_alarm(@Body Request_alarm request_alarm);

}
