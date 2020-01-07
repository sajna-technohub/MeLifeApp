package com.technohub.melifeapp.models;

public class QuestionModel {
    int Qid;
    int Qno;
    String Qname;

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

    public int getQid() {
        return Qid;
    }

    public void setQid(int qid) {
        Qid = qid;
    }
}
