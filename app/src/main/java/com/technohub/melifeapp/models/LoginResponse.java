package com.technohub.melifeapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Query;

public class LoginResponse {

    public String ErrorCode;
    public String Message;
    List<User> Data=new ArrayList<User>();

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<User> getData() {
        return Data;
    }

    public void setData(List<User> data) {
        Data = data;
    }

    SharedPreferences sharedPreferences;
    public void setSharedPreferences(Context context,String name,int userid,String completion_status,String token) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString("name", name).apply();
        sharedPreferences.edit().putString("userid", userid+"").apply();
        sharedPreferences.edit().putString("completion_status", completion_status).apply();
        sharedPreferences.edit().putString("token",  token).apply();

    }

    public String getSharedPreferences(Context context,String key)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key,"");
    }
}
