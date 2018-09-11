package com.example.soring.bandcv12.Model;

import com.google.gson.annotations.SerializedName;

public class Request_Motor {
    @SerializedName("rsl")
    private String rsl;

    public Request_Motor(){}

    public Request_Motor(String rsl) {
        this.rsl = rsl;
    }

    public String getRsl() {
        return rsl;
    }

    public void setRsl(String rsl) {
        this.rsl = rsl;
    }
}
