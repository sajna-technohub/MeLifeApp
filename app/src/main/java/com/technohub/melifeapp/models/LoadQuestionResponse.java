package com.technohub.melifeapp.models;

import java.util.ArrayList;

public class LoadQuestionResponse {

    private float ErrorCode;
    private String Message;
    private String pagination;
    ArrayList< TotalQuestionsModel > examquestionData = new ArrayList < TotalQuestionsModel > ();
    private float total_no_questions;
    private String test_id;
    private float limit;
    private float remainCount;
    private float reviewCount;
    private float log_id;
    Qtn_time_taken Qtn_time_takenObject;
    private String is_popup_display;
    private String idle_log_id;
    private String log_qn_id;
    private String log_option_id;
    private String log_attended_time;
    private String last_question_id;

    public float getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(float errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public ArrayList<TotalQuestionsModel> getExamquestionData() {
        return examquestionData;
    }

    public void setExamquestionData(ArrayList<TotalQuestionsModel> examquestionData) {
        this.examquestionData = examquestionData;
    }

    public float getTotal_no_questions() {
        return total_no_questions;
    }

    public void setTotal_no_questions(float total_no_questions) {
        this.total_no_questions = total_no_questions;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public float getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(float remainCount) {
        this.remainCount = remainCount;
    }

    public float getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(float reviewCount) {
        this.reviewCount = reviewCount;
    }

    public float getLog_id() {
        return log_id;
    }

    public void setLog_id(float log_id) {
        this.log_id = log_id;
    }

    public Qtn_time_taken getQtn_time_takenObject() {
        return Qtn_time_takenObject;
    }

    public void setQtn_time_takenObject(Qtn_time_taken qtn_time_takenObject) {
        Qtn_time_takenObject = qtn_time_takenObject;
    }

    public String getIs_popup_display() {
        return is_popup_display;
    }

    public void setIs_popup_display(String is_popup_display) {
        this.is_popup_display = is_popup_display;
    }

    public String getIdle_log_id() {
        return idle_log_id;
    }

    public void setIdle_log_id(String idle_log_id) {
        this.idle_log_id = idle_log_id;
    }

    public String getLog_qn_id() {
        return log_qn_id;
    }

    public void setLog_qn_id(String log_qn_id) {
        this.log_qn_id = log_qn_id;
    }

    public String getLog_option_id() {
        return log_option_id;
    }

    public void setLog_option_id(String log_option_id) {
        this.log_option_id = log_option_id;
    }

    public String getLog_attended_time() {
        return log_attended_time;
    }

    public void setLog_attended_time(String log_attended_time) {
        this.log_attended_time = log_attended_time;
    }

    public String getLast_question_id() {
        return last_question_id;
    }

    public void setLast_question_id(String last_question_id) {
        this.last_question_id = last_question_id;
    }
}
