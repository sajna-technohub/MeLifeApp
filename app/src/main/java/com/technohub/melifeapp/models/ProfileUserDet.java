package com.technohub.melifeapp.models;

public class ProfileUserDet {
    private String name;
    private String melife_user_id;
    private String email;
    private String pincode;
    private String father_name;
    private String mother_name;
    private String father_prof;
    private String mother_prof;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getFather_prof() {
        return father_prof;
    }

    public void setFather_prof(String father_prof) {
        this.father_prof = father_prof;
    }

    public String getMother_prof() {
        return mother_prof;
    }

    public void setMother_prof(String mother_prof) {
        this.mother_prof = mother_prof;
    }

    public String getMelife_user_id() {
        return melife_user_id;
    }

    public void setMelife_user_id(String melife_user_id) {
        this.melife_user_id = melife_user_id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getProfile_icon() {
        return profile_icon;
    }

    public void setProfile_icon(String profile_icon) {
        this.profile_icon = profile_icon;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    private String dob;
    private String country;
    private String state;
    private String profile_icon;
    private String mobile_no;
    private String qualification;
}
