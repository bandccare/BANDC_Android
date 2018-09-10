package com.example.soring.bandcv12.Model;

public class Response_Oauth {
    private String access_token;
    private int expires_in;
    private String referesh_token;
    private String token_type;
    private String id_token;

    public Response_Oauth(){}

    public Response_Oauth(String access_token, int expires_in, String referesh_token, String token_type, String id_token) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.referesh_token = referesh_token;
        this.token_type = token_type;
        this.id_token = id_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getReferesh_token() {
        return referesh_token;
    }

    public void setReferesh_token(String referesh_token) {
        this.referesh_token = referesh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}
