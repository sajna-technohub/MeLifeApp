package com.technohub.melifeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveAnswerResponse implements Parcelable {
    private String ErrorCode;
    private String Message;
    private SaveData record;


    protected SaveAnswerResponse(Parcel in) {
        ErrorCode = in.readString();
        Message = in.readString();
    }

    public static final Creator<SaveAnswerResponse> CREATOR = new Creator<SaveAnswerResponse>() {
        @Override
        public SaveAnswerResponse createFromParcel(Parcel in) {
            return new SaveAnswerResponse(in);
        }

        @Override
        public SaveAnswerResponse[] newArray(int size) {
            return new SaveAnswerResponse[size];
        }
    };

    public String getMessage() {
        return Message;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public SaveData getRecord() {
        return record;
    }

    public void setRecord(SaveData record) {
        this.record = record;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ErrorCode);
        dest.writeString(Message);
    }
}
