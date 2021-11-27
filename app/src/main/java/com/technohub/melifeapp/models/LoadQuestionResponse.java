package com.technohub.melifeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class LoadQuestionResponse implements Parcelable {

    private String ErrorCode;
    private String Message;
    private ArrayList record=new ArrayList();
    private String suggeted_career_image;
    private String pagination;
    ArrayList< TotalQuestionsModel > examquestionData = new ArrayList < TotalQuestionsModel > ();
    private String total_no_questions;
    private String test_id;
    private String limit;
    private String remainCount;
    private String reviewCount;
    private String log_id;
    Qtn_time_taken Qtn_time_takenObject;
    private String is_popup_display;
    private String idle_log_id;
    private String log_qn_id;
    private String log_option_id;
    private String log_attended_time;
    private String last_question_id;
    private String no_of_options_current_questions;
    private String imgStatusQns;
    private String imgStatusOpts;
    private String eqStatusOpts;
    private String lastQnflag;

    public ArrayList getRecord() {
        return record;
    }

    public void setRecord(ArrayList record) {
        this.record = record;
    }

    public String getSuggeted_career_image() {
        return suggeted_career_image;
    }

    public void setSuggeted_career_image(String suggeted_career_image) {
        this.suggeted_career_image = suggeted_career_image;
    }

    public String getCurrent_qns() {
        return current_qns;
    }

    public void setCurrent_qns(String current_qns) {
        this.current_qns = current_qns;
    }

    private String eqStatusOptsQns;
    private String current_qns;
    private String next_record;

    public String getLastQnflag() {
        return lastQnflag;
    }

    public void setLastQnflag(String lastQnflag) {
        this.lastQnflag = lastQnflag;
    }

    public String getNext_record() {
        return next_record;
    }

    public void setNext_record(String next_record) {
        this.next_record = next_record;
    }

    public static Creator<LoadQuestionResponse> getCREATOR() {
        return CREATOR;
    }

    protected LoadQuestionResponse(Parcel in) {
        ErrorCode = in.readString();
        Message = in.readString();
        pagination = in.readString();
        total_no_questions = in.readString();
        test_id = in.readString();
        limit = in.readString();
        remainCount = in.readString();
        reviewCount = in.readString();
        log_id = in.readString();
        is_popup_display = in.readString();
        idle_log_id = in.readString();
        log_qn_id = in.readString();
        log_option_id = in.readString();
        log_attended_time = in.readString();
        last_question_id = in.readString();
        no_of_options_current_questions = in.readString();
        imgStatusQns = in.readString();
        imgStatusOpts = in.readString();
        eqStatusOpts = in.readString();
        eqStatusOptsQns = in.readString();
        current_qns = in.readString();
        next_record = in.readString();
    }

    public static final Creator<LoadQuestionResponse> CREATOR = new Creator<LoadQuestionResponse>() {
        @Override
        public LoadQuestionResponse createFromParcel(Parcel in) {
            return new LoadQuestionResponse(in);
        }

        @Override
        public LoadQuestionResponse[] newArray(int size) {
            return new LoadQuestionResponse[size];
        }
    };

    public String getNo_of_options_current_questions() {
        return no_of_options_current_questions;
    }

    public void setNo_of_options_current_questions(String no_of_options_current_questions) {
        this.no_of_options_current_questions = no_of_options_current_questions;
    }

    public String getImgStatusQns() {
        return imgStatusQns;
    }

    public void setImgStatusQns(String imgStatusQns) {
        this.imgStatusQns = imgStatusQns;
    }

    public String getImgStatusOpts() {
        return imgStatusOpts;
    }

    public void setImgStatusOpts(String imgStatusOpts) {
        this.imgStatusOpts = imgStatusOpts;
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

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getTotal_no_questions() {
        return total_no_questions;
    }

    public void setTotal_no_questions(String total_no_questions) {
        this.total_no_questions = total_no_questions;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(String remainCount) {
        this.remainCount = remainCount;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public void setExamquestionData(ArrayList<TotalQuestionsModel> examquestionData) {
        this.examquestionData = examquestionData;
    }



    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }


    public String getEqStatusOpts() {
        return eqStatusOpts;
    }

    public void setEqStatusOpts(String eqStatusOpts) {
        this.eqStatusOpts = eqStatusOpts;
    }

    public String getEqStatusOptsQns() {
        return eqStatusOptsQns;
    }

    public void setEqStatusOptsQns(String eqStatusOptsQns) {
        this.eqStatusOptsQns = eqStatusOptsQns;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ErrorCode);
        dest.writeString(Message);
        dest.writeString(pagination);
        dest.writeString(total_no_questions);
        dest.writeString(test_id);
        dest.writeString(limit);
        dest.writeString(remainCount);
        dest.writeString(reviewCount);
        dest.writeString(log_id);
        dest.writeString(is_popup_display);
        dest.writeString(idle_log_id);
        dest.writeString(log_qn_id);
        dest.writeString(log_option_id);
        dest.writeString(log_attended_time);
        dest.writeString(last_question_id);
        dest.writeString(no_of_options_current_questions);
        dest.writeString(imgStatusQns);
        dest.writeString(imgStatusOpts);
        dest.writeString(eqStatusOptsQns);
        dest.writeString(eqStatusOpts);
        dest.writeString(current_qns);
        dest.writeString(next_record);
    }
}
