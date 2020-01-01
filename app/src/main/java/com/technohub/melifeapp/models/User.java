package com.technohub.melifeapp.models;

public class User {
    private String name;
    private String email;
    private String mobile;
    private String pincode;
    private String password;
    /*User Sign Up*/

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
}
