package com.technohub.melifeapp.models;

import java.util.ArrayList;

public class DisplayData {
    private String user_id;
    private String useremail;
    private String test_id;
    private String totalCount;
    private String remainCount;
    private String exam_id;
    private String last_qn_no;
    private String qn_id;
    private String qn_flag;
    private String slctd_optn;
    Total_time_taken_question Total_time_taken_questionObject;
    private String last_qn_order;
    private String is_popup_display;
    ArrayList< FeedBackType > fb_type = new ArrayList < FeedBackType > ();
    ArrayList < FeedBackMenu > fb_menu = new ArrayList < FeedBackMenu > ();


    // Getter Methods

    public String getUser_id() {
        return user_id;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getTest_id() {
        return test_id;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(String remainCount) {
        this.remainCount = remainCount;
    }

    public String getQn_flag() {
        return qn_flag;
    }

    public void setQn_flag(String qn_flag) {
        this.qn_flag = qn_flag;
    }

    public Total_time_taken_question getTotal_time_taken_questionObject() {
        return Total_time_taken_questionObject;
    }

    public void setTotal_time_taken_questionObject(Total_time_taken_question total_time_taken_questionObject) {
        Total_time_taken_questionObject = total_time_taken_questionObject;
    }

    public ArrayList<FeedBackType> getFb_type() {
        return fb_type;
    }

    public void setFb_type(ArrayList<FeedBackType> fb_type) {
        this.fb_type = fb_type;
    }

    public ArrayList<FeedBackMenu> getFb_menu() {
        return fb_menu;
    }

    public void setFb_menu(ArrayList<FeedBackMenu> fb_menu) {
        this.fb_menu = fb_menu;
    }

    public String getExam_id() {
        return exam_id;
    }

    public String getLast_qn_no() {
        return last_qn_no;
    }

    public String getQn_id() {
        return qn_id;
    }



    public String getSlctd_optn() {
        return slctd_optn;
    }

    public Total_time_taken_question getTotal_time_taken_question() {
        return Total_time_taken_questionObject;
    }

    public String getLast_qn_order() {
        return last_qn_order;
    }

    public String getIs_popup_display() {
        return is_popup_display;
    }

    // Setter Methods

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }


    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public void setLast_qn_no(String last_qn_no) {
        this.last_qn_no = last_qn_no;
    }

    public void setQn_id(String qn_id) {
        this.qn_id = qn_id;
    }



    public void setSlctd_optn(String slctd_optn) {
        this.slctd_optn = slctd_optn;
    }

    public void setTotal_time_taken_question(Total_time_taken_question total_time_taken_questionObject) {
        this.Total_time_taken_questionObject = total_time_taken_questionObject;
    }

    public void setLast_qn_order(String last_qn_order) {
        this.last_qn_order = last_qn_order;
    }

    public void setIs_popup_display(String is_popup_display) {
        this.is_popup_display = is_popup_display;
    }
}

