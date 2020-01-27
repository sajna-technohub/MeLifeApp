package com.technohub.melifeapp.models;

import java.util.ArrayList;
import java.util.List;

public class TestcategoryResponse {
    public int ErrorCode;
    public String Message;
    private String Userid;
    public  String Useremail;
    List<Tests> Data=new ArrayList();

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getUseremail() {
        return Useremail;
    }

    public void setUseremail(String useremail) {
        Useremail = useremail;
    }


    public List<Tests> getData() {
        return Data;
    }

    public void setData(List<Tests> data) {
        Data = data;
    }

    public TestcategoryResponse() {
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


}
