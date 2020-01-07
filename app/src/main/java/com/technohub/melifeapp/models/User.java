package com.technohub.melifeapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {
    public String name;
    public String email;
    public String mobile;
    public String pincode;
    public String password;
    public String token;
    public int melife_user_id;
    private String completion_status;

    /*User Sign Up*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public User(String name, String email, String mobile, String pincode) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.pincode = pincode;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }



    public int getMelife_user_id() {
        return melife_user_id;
    }

    public void setMelife_user_id(int melife_user_id) {
        this.melife_user_id = melife_user_id;
    }

    public String getCompletion_status() {
        return completion_status;
    }

    public User(String email, int melife_user_id, String completion_status) {
        this.email = email;
        this.melife_user_id = melife_user_id;
        this.completion_status = completion_status;
    }

    public User() {
    }

    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }
    public void setSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString("apiToken", this.token).apply();
        sharedPreferences.edit().putString("name", this.name).apply();
        sharedPreferences.edit().putString("userid", this.melife_user_id+"").apply();
        sharedPreferences.edit().putString("status", this.completion_status).apply();
    }
}
