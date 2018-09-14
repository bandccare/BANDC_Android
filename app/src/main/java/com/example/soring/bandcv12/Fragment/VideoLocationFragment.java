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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoLocationFragment extends Fragment implements OnMapReadyCallback {

    private RtspViewPlayer playView;
    private RelativeLayout surfaceView;

    Button left_btn;
    Button right_btn;
    Button right_center_btn;
    Button left_center_btn;
    Button center_btn;

    private static VideoLocationFragment instance;
    MapView mapView;

    public static VideoLocationFragment getInstance() {
        if(instance == null) instance = new VideoLocationFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_location, container, false);

        mapView = (MapView)view.findViewById(R.id.main_google_map);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
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
                Call<Request_Motor> response = RetrofitClient.getInstance().getService2().Motor_Controller(request_motor);
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
                Call<Request_Motor> response = RetrofitClient.getInstance().getService2().Motor_Controller(request_motor);
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
                Call<Request_Motor> response = RetrofitClient.getInstance().getService2().Motor_Controller(request_motor);
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
                Call<Request_Motor> response = RetrofitClient.getInstance().getService2().Motor_Controller(request_motor);
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
                Call<Request_Motor> response = RetrofitClient.getInstance().getService2().Motor_Controller(request_motor);
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

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.581783, 127.009836), 14);
        googleMap.animateCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.581783, 127.009836))
                .title("착용자 위치"));
    }
}
