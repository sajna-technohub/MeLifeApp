package com.technohub.melifeapp.models;

import java.util.ArrayList;

public class TotalQuestionsModel {
    private String exam_question_id;
    private String question_order;
    private String is_instruction_display;
    private String question_id;
    private String mcq_question_id;
    private String question;
    private String paragraph;
    private String question_type;
    private String instruction;
    private String section_id;
    private String section_name;
    private String description;
    private String test_name;
    ArrayList< All_questions > all_questions = new ArrayList < All_questions > ();
    ArrayList < AnswerOptions > options = new ArrayList < AnswerOptions > ();
    private String selected_option;


    public ArrayList<All_questions> getAll_questions() {
        return all_questions;
    }

    public void setAll_questions(ArrayList<All_questions> all_questions) {
        this.all_questions = all_questions;
    }

    public ArrayList<AnswerOptions> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<AnswerOptions> options) {
        this.options = options;
    }

    public String getSelected_option() {
        return selected_option;
    }

    public void setSelected_option(String selected_option) {
        this.selected_option = selected_option;
    }

    public String getExam_question_id() {
        return exam_question_id;
    }

    public void setExam_question_id(String exam_question_id) {
        this.exam_question_id = exam_question_id;
    }

    public String getQuestion_order() {
        return question_order;
    }

    public void setQuestion_order(String question_order) {
        this.question_order = question_order;
    }

    public String getIs_instruction_display() {
        return is_instruction_display;
    }

    public void setIs_instruction_display(String is_instruction_display) {
        this.is_instruction_display = is_instruction_display;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
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

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }
}
