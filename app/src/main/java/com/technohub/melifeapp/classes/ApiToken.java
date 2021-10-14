package com.technohub.melifeapp.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.annotations.SerializedName;

public class ApiToken {
    @SerializedName("token")
    private String token;
    @SerializedName("userid")
    public String userid;
    @SerializedName("email")
    private String email;
    @SerializedName("completion_status")
    private String completion_status;
    public String getUserid() {
        return userid;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompletion_status() {
        return completion_status;
    }

    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }
    public String getToken() {
        return token;
    }

    public void setSharedPreferences(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString("apiToken", this.token).apply();
        sharedPreferences.edit().putString("userid", this.userid).apply();
        sharedPreferences.edit().putString("status", this.completion_status).apply();
    }
}
