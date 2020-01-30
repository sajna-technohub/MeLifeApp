package com.technohub.melifeapp.models;

import java.util.ArrayList;
import java.util.List;

public class Data {
    List<ProfileModel> UserDetails=new ArrayList();

    public List<ProfileModel> getUserDetails() {
        return UserDetails;
    }

    public void setUserDetails(List<ProfileModel> userDetails) {
        UserDetails = userDetails;
    }
}
