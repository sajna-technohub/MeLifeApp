package com.technohub.melifeapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileResponse {

    public int ErrorCode;
    public String Message;
    ProfileModel Data=new ProfileModel();
    List<Country> Country=new ArrayList();
    List<State> State=new ArrayList();
    List<Qualification> Qualification=new ArrayList();
   public ArrayList < ProfileAcademicDet > academic_details_current = new ArrayList < ProfileAcademicDet > ();
    public ArrayList < ProfileAcademicDet > academic_details_previous = new ArrayList < ProfileAcademicDet > ();

    public List<com.technohub.melifeapp.models.Country> getCountry() {
        return Country;
    }

    public ArrayList<ProfileAcademicDet> getAcademic_details_current() {
        return academic_details_current;
    }

    public void setAcademic_details_current(ArrayList<ProfileAcademicDet> academic_details_current) {
        this.academic_details_current = academic_details_current;
    }

    public ArrayList<ProfileAcademicDet> getAcademic_details_previous() {
        return academic_details_previous;
    }

    public void setAcademic_details_previous(ArrayList<ProfileAcademicDet> academic_details_previous) {
        this.academic_details_previous = academic_details_previous;
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

    public ProfileModel getData() {
        return Data;
    }

    public void setData(ProfileModel data) {
        Data = data;
    }
}
