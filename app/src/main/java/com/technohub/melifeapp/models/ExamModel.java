package com.technohub.melifeapp.models;

public class ExamModel {
    int QId;
    int Qno;
    String Qname;
    int ansId,sectionId;
    String answer;

    public int getQno() {
        return Qno;
    }

    public void setQno(int qno) {
        Qno = qno;
    }

    public String getQname() {
        return Qname;
    }

    public void setQname(String qname) {
        Qname = qname;
    }

    public int getQId() {
        return QId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setQId(int QId) {
        this.QId = QId;
    }
}
