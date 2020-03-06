package com.technohub.melifeapp.models;

public class All_questions {
    private String exam_question_id;
    private String mcq_question_id;
    private  String question;
    private String selected_option;

    public String getExam_question_id() {
        return exam_question_id;
    }

    public void setExam_question_id(String exam_question_id) {
        this.exam_question_id = exam_question_id;
    }

    public String getMcq_question_id() {
        return mcq_question_id;
    }

    public void setMcq_question_id(String mcq_question_id) {
        this.mcq_question_id = mcq_question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelected_option() {
        return selected_option;
    }

    public void setSelected_option(String selected_option) {
        this.selected_option = selected_option;
    }
}
