package com.example.soring.bandcv12.NDK;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RtspViewPlayer extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "RtspPlayView";

    private SurfaceHolder mHolder;
    private NDKAdapter mPlayerNDKAdapter;

    public RtspViewPlayer(Context context, String uri){
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);

        mPlayerNDKAdapter = new NDKAdapter();
        Log.d("datasource",uri+"");
        NDKAdapter.setDataSource(uri);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               // NDKAdapter.play(mHolder.getSurface());
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
