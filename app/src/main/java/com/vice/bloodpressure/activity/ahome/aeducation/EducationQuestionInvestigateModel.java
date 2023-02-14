package com.vice.bloodpressure.activity.ahome.aeducation;

public class EducationQuestionInvestigateModel {
    public EducationQuestionInvestigateModel(String text, boolean isCheck) {
        this.text = text;
        this.isCheck = isCheck;
    }

    private String text;
    private boolean isCheck;

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
}
