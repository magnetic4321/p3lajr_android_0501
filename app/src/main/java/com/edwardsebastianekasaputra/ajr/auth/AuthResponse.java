package com.edwardsebastianekasaputra.ajr.auth;

import com.edwardsebastianekasaputra.ajr.models.User;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    private String message;

    @SerializedName("user")
    private User user;

    private String access_token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
