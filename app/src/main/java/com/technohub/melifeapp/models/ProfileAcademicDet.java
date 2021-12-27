package com.technohub.melifeapp.models;

public class ProfileAcademicDet {
    public String name;
    public String y;

    public String getName() {
        return name;
    }

    public ProfileAcademicDet(String name, String y) {
        this.name = name;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
