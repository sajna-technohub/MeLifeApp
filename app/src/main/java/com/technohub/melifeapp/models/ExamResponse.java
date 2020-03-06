package com.technohub.melifeapp.models;
import java.util.List;

public class ExamResponse
{
    List<ExamModel> examList;

    public List<ExamModel> getExamList() {
        return examList;
    }

    public void setExamList(List<ExamModel> examList) {
        this.examList = examList;
    }

    private String ErrorCode;
    private String Message;
    private DisplayData displayData;
    private String examCompletionsts;
    private String qnExiststs;
    // Getter Methods



    public String getMessage() {
        return Message;
    }



    public String getExamCompletionsts() {
        return examCompletionsts;
    }

    public String getQnExiststs() {
        return qnExiststs;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public DisplayData getDisplayData() {
        return displayData;
    }

    public void setDisplayData(DisplayData displayData) {
        this.displayData = displayData;
    }

    public void setQnExiststs(String qnExiststs) {
        this.qnExiststs = qnExiststs;
    }

    // Setter Methods



    public void setMessage(String Message) {
        this.Message = Message;
    }



    public void setExamCompletionsts(String examCompletionsts) {
        this.examCompletionsts = examCompletionsts;
    }


}
