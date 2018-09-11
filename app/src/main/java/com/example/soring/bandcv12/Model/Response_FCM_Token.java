package com.example.soring.bandcv12.Model;

public class Response_FCM_Token {
    private String user_id;

    public Response_FCM_Token(){}

    public Response_FCM_Token(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
