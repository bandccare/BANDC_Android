package com.example.soring.bandcv12.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.soring.bandcv12.NDK.RtspViewPlayer;
import com.example.soring.bandcv12.R;

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

        //NDK
        playView = new RtspViewPlayer(getContext(),"rtsp://192.168.0.2:8091/rtsp");
        surfaceView = view.findViewById(R.id.surface_video);
        surfaceView.addView(playView);

        return view;
    }

}
