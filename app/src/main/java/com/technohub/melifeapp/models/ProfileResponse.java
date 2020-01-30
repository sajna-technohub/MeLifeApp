package com.technohub.melifeapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileResponse {
    public int ErrorCode;
    public String Message;
    List<ProfileModel> Data=new ArrayList();
    List<Country> Country=new ArrayList();
    List<State> State=new ArrayList();
    List<Qualification> Qualification=new ArrayList();


    public List<com.technohub.melifeapp.models.Country> getCountry() {
        return Country;
    }

    public void setCountry(List<com.technohub.melifeapp.models.Country> country) {
        Country = country;
    }

    public List<com.technohub.melifeapp.models.State> getState() {
        return State;
    }

    public void setState(List<com.technohub.melifeapp.models.State> state) {
        State = state;
    }

    public List<com.technohub.melifeapp.models.Qualification> getQualification() {
        return Qualification;
    }

    public void setQualification(List<com.technohub.melifeapp.models.Qualification> qualification) {
        Qualification = qualification;
    }

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
