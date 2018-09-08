package com.example.soring.bandcv12.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.soring.bandcv12.Fragment.LocationFragment;
import com.example.soring.bandcv12.Fragment.VideoPlayerFragment;
import com.example.soring.bandcv12.R;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUM = 2;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return VideoPlayerFragment.getInstance();
            case 1:
                return LocationFragment.getInstance();
            default:
                return null;
        }
    }

    public String getPageTitle(int position){
        switch(position){
            case 0:
                return "VIDEO";
            case 1:
                return "LOCATION";
            default:
                return null;
        }
    }

    public int getIcon(int position){
        switch(position){
            case 0:
                return R.drawable.tab_video;
            case 1:
                return R.drawable.tab_location;
            default:
                return 0;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}
