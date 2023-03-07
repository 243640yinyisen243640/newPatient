package com.vice.bloodpressure.model;

public class EducationQuestionInvestigateModel {
    public EducationQuestionInvestigateModel(String text, boolean isCheck) {
        this.text = text;
        this.isCheck = isCheck;
    }

    private String text;
    private String id;
    private boolean isCheck;


    public EducationQuestionInvestigateModel(String text, String id, boolean isCheck) {
        this.text = text;
        this.id = id;
        this.isCheck = isCheck;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
