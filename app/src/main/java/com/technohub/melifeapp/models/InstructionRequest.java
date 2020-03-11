package com.technohub.melifeapp.models;

public class InstructionRequest {
    private String sec_ids;
    private String exam_id;
    private String DeviceType;
    private String DeviceToken;

    public String getSec_ids() {
        return sec_ids;
    }

    public void setSec_ids(String sec_ids) {
        this.sec_ids = sec_ids;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
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
