package com.technohub.melifeapp.models;

public class ProfileModel {
    String name;
    String email;
    String pincode;
    String dob;
    String country;
    String state;
    String qualification;
    String DeviceType;
    String DeviceToken;
    String profile_icon;
    int melife_user_id;
    String completion_status;
    String mobile_no;
    String user_id;
    String date;
    String mobno;
    String country_name;

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDob() {
        return dob;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfile_icon() {
        return profile_icon;
    }

    public void setProfile_icon(String profile_icon) {
        this.profile_icon = profile_icon;
    }

    public int getMelife_user_id() {
        return melife_user_id;
    }

    public void setMelife_user_id(int melife_user_id) {
        this.melife_user_id = melife_user_id;
    }

    public int getUserid() {
        return melife_user_id;
    }

    public void setUserid(int userid) {
        this.melife_user_id = userid;
    }

    public String getCompletion_status() {
        return completion_status;
    }

    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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
}
