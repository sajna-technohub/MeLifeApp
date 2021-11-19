package com.technohub.melifeapp.models;

import android.icu.text.AlphabeticIndex;

public class StreamPieChartResponse {
    private float ErrorCode;
    private String Message;
    private StreamPieRecord record;
    private StreamPieResult result;

    public float getErrorCode() {
        return ErrorCode;
    }

    public StreamPieRecord getRecord() {
        return record;
    }

    public void setRecord(StreamPieRecord record) {
        this.record = record;
    }

    public String getMessage() {
        return Message;
    }

    public StreamPieResult getResult() {
        return result;
    }

    public void setResult(StreamPieResult result) {
        this.result = result;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setErrorCode(float errorCode) {
        ErrorCode = errorCode;
    }
}
