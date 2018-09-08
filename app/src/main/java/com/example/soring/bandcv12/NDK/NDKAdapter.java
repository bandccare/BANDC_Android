package com.example.soring.bandcv12.NDK;

public class NDKAdapter {

    static {
        System.loadLibrary("VideoPlayer");
    }

    public static native void setDataSource(String uri);
    public static native int play(Object surface);

    public NDKAdapter(){

    }

}
