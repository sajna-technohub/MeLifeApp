package com.technohub.melifeapp.models;

public class Tests {
   public int test_id;
   public String test_name;
   public String cmplts_status;
   public int exam_id;

    public String getCmplts_status() {
        return cmplts_status;
    }

    public void setCmplts_status(String cmplts_status) {
        this.cmplts_status = cmplts_status;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }


}
