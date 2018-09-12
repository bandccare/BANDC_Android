package com.example.soring.bandcv12.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BPM_value {
    @SerializedName("value")
    private ArrayList<BPM_fpVal> fpValList;

    public BPM_value(){}

    public BPM_value(ArrayList<BPM_fpVal> fpValList) {
        this.fpValList = fpValList;
    }

    public ArrayList<BPM_fpVal> getFpValList() {
        return fpValList;
    }

    public void setFpValList(ArrayList<BPM_fpVal> fpValList) {
        this.fpValList = fpValList;
    }
}
