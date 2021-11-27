package com.technohub.melifeapp.models;

import java.util.List;

public class InterestAreaImgResponse {

    public int ErrorCode;

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

    public List<Object> getRecord() {
        return record;
    }

    public void setRecord(List<Object> record) {
        this.record = record;
    }

    public String getSuggeted_career_image() {
        return suggeted_career_image;
    }

    public void setSuggeted_career_image(String suggeted_career_image) {
        this.suggeted_career_image = suggeted_career_image;
    }

    public String Message;
    public List<Object> record;
    public String suggeted_career_image;
}
