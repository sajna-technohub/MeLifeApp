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

    private float ErrorCode;
    private String Message;
    Data data;
    private String examCompletionsts;
    private float qnExiststs;
    // Getter Methods

    public float getErrorCode() {
        return ErrorCode;
    }

    public String getMessage() {
        return Message;
    }

    public Data getData() {
        return data;
    }

    public String getExamCompletionsts() {
        return examCompletionsts;
    }

    public float getQnExiststs() {
        return qnExiststs;
    }

    // Setter Methods

    public void setErrorCode(float ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setData(Data DataObject) {
        this.data = DataObject;
    }

    public void setExamCompletionsts(String examCompletionsts) {
        this.examCompletionsts = examCompletionsts;
    }

    public void setQnExiststs(float qnExiststs) {
        this.qnExiststs = qnExiststs;
    }
}
