package com.example.soring.bandcv12.Model;

public class Request_FCM_Token {
    private String user_token;
    private String user_id;

    public Request_FCM_Token(){}

    public Request_FCM_Token(String user_token, String user_id) {
        this.user_token = user_token;
        this.user_id = user_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
