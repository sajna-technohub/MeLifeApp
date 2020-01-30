package com.technohub.melifeapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {

    public String name;
    public String user_email;
    public String mobile;
    public String mobno;
    public String email;
    public String pincode;
    public String user_Password;
    public String token;
    public String DeviceType;
    public String DeviceToken;
    public String state;
    public String country;
    public String qualification;
    public int melife_user_id;
    String userid;
    int user_id;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    String date;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String completion_status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    /*User Sign Up*/

    public String getName() {
        return name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
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



    public User() {
    }

    public User(String name, String user_email, String mobile) {
        this.name = name;
        this.user_email = user_email;
        this.mobile = mobile;
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
