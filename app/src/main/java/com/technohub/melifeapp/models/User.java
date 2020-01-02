package com.technohub.melifeapp.models;

public class User {
    public String name;
    public String email;
    public String mobile;
    public String pincode;
    public String password;
    public int salary;
    public int age;
    public int id;
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

    public User(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public User(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public User(String name, int salary, int age, int id) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.id = id;
    }
}
