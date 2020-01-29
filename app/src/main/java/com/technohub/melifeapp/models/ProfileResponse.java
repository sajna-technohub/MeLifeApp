package com.technohub.melifeapp.models;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {
    public int ErrorCode;
    public String Message;
    List<ProfileModel> Data=new ArrayList();

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


    public List<ProfileModel> getData() {
        return Data;
    }

    public void setData(List<ProfileModel> data) {
        Data = data;
    }
}
