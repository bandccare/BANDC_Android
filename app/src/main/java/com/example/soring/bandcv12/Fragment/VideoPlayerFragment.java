package com.example.soring.bandcv12.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.soring.bandcv12.Model.Request_Motor;
import com.example.soring.bandcv12.NDK.RtspViewPlayer;
import com.example.soring.bandcv12.R;
import com.example.soring.bandcv12.Util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoPlayerFragment extends Fragment {

    private static VideoPlayerFragment instance;

    private RtspViewPlayer playView;
    private RelativeLayout surfaceView;

    Button left_btn;
    Button right_btn;
    Button right_center_btn;
    Button left_center_btn;
    Button center_btn;

    public static VideoPlayerFragment getInstance() {
        if(instance == null) instance = new VideoPlayerFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_player, container, false);

        left_btn = (Button)view.findViewById(R.id.left_btn);
        right_btn = (Button)view.findViewById(R.id.right_btn);
        right_center_btn = (Button)view.findViewById(R.id.right_center_btn);
        left_center_btn = (Button)view.findViewById(R.id.left_center_btn);
        center_btn = (Button)view.findViewById(R.id.center_btn);



        //NDK
        playView = new RtspViewPlayer(getContext(),"rtsp://192.168.0.2:8091/rtsp");
        surfaceView = view.findViewById(R.id.surface_video);
        surfaceView.addView(playView);

        final Request_Motor request_motor = new Request_Motor();



        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_motor.setRsl("left");
                Call<Request_Motor> response = RetrofitClient.getInstance().getService().Motor_Controller(request_motor);
                response.enqueue(new Callback<Request_Motor>() {
                    @Override
                    public void onResponse(Call<Request_Motor> call, Response<Request_Motor> response) {

                    }

                    @Override
                    public void onFailure(Call<Request_Motor> call, Throwable t) {

                    }
                });
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_motor.setRsl("right");
                Call<Request_Motor> response = RetrofitClient.getInstance().getService().Motor_Controller(request_motor);
                response.enqueue(new Callback<Request_Motor>() {
                    @Override
                    public void onResponse(Call<Request_Motor> call, Response<Request_Motor> response) {

                    }

                    @Override
                    public void onFailure(Call<Request_Motor> call, Throwable t) {

                    }
                });

            }
        });

        right_center_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_motor.setRsl("right_center");
                Call<Request_Motor> response = RetrofitClient.getInstance().getService().Motor_Controller(request_motor);
                response.enqueue(new Callback<Request_Motor>() {
                    @Override
                    public void onResponse(Call<Request_Motor> call, Response<Request_Motor> response) {

                    }

                    @Override
                    public void onFailure(Call<Request_Motor> call, Throwable t) {

                    }
                });

            }
        });

        left_center_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_motor.setRsl("left_center");
                Call<Request_Motor> response = RetrofitClient.getInstance().getService().Motor_Controller(request_motor);
                response.enqueue(new Callback<Request_Motor>() {
                    @Override
                    public void onResponse(Call<Request_Motor> call, Response<Request_Motor> response) {

                    }

                    @Override
                    public void onFailure(Call<Request_Motor> call, Throwable t) {

                    }
                });

            }
        });

        center_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_motor.setRsl("center");
                Call<Request_Motor> response = RetrofitClient.getInstance().getService().Motor_Controller(request_motor);
                response.enqueue(new Callback<Request_Motor>() {
                    @Override
                    public void onResponse(Call<Request_Motor> call, Response<Request_Motor> response) {

                    }

                    @Override
                    public void onFailure(Call<Request_Motor> call, Throwable t) {

                    }
                });

            }
        });
        return view;
    }

}
