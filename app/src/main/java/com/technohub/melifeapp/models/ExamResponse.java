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
}
