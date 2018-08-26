package com.example.soring.bandcv12.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Retrofit_BPM_Model {

    private String minStartTimeNs;
    private String maxEndTimeNs;
    private String dataSourceId;
    @SerializedName("point")
    private ArrayList<BPM_value> point;

    public Retrofit_BPM_Model(String minStartTimeNs, String maxEndTimeNs, String dataSourceId, ArrayList<BPM_value> point) {
        this.minStartTimeNs = minStartTimeNs;
        this.maxEndTimeNs = maxEndTimeNs;
        this.dataSourceId = dataSourceId;
        this.point = point;
    }

    public String getMinStartTimeNs() {
        return minStartTimeNs;
    }

    public void setMinStartTimeNs(String minStartTimeNs) {
        this.minStartTimeNs = minStartTimeNs;
    }

    public String getMaxEndTimeNs() {
        return maxEndTimeNs;
    }

    public void setMaxEndTimeNs(String maxEndTimeNs) {
        this.maxEndTimeNs = maxEndTimeNs;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public ArrayList<BPM_value> getPoint() {
        return point;
    }

    public void setPoint(ArrayList<BPM_value> point) {
        this.point = point;
    }
}
