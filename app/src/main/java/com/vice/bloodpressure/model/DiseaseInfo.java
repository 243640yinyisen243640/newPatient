package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DiseaseInfo {
    /**
     * 1~6* 1 糖尿病
     * * 2 高血压
     * * 3 糖尿病前期
     * * 4 冠心病
     * * 5 脑卒中
     * * 6 慢阻肺
     */
    private String diseaseType1 = "0";
    private String diseaseType2 = "0";
    private String diseaseType3 = "0";
    private String diseaseType4 = "0";
    private String diseaseType5 = "0";
    private String diseaseType6 = "0";

    /**
     * 并发症
     */
    private String complicationName;
    /**
     * 疾病类型
     * * 1 糖尿病
     * * 2 高血压
     * * 3 糖尿病前期
     * * 4 冠心病
     * * 5 脑卒中
     * * 6 慢阻肺
     */
    private String complicationType;
    private String isConfirm;
    private String level;

    /**
     * 疾病类型
     */
    private String diseaseType;
    /**
     * 疾病等级
     */
    private String diseaseChildType;


    /**
     * 高血压程度
     */
    private String diseaseRisk;
    /**
     * 诊断日期
     */
    private String diagnoseDate;
    private String complicationDate;

    public String getComplicationDate() {
        return complicationDate;
    }

    public void setComplicationDate(String complicationDate) {
        this.complicationDate = complicationDate;
    }

    public String getDiseaseType1() {
        return diseaseType1;
    }

    public void setDiseaseType1(String diseaseType1) {
        this.diseaseType1 = diseaseType1;
    }

    public String getDiseaseType2() {
        return diseaseType2;
    }

    public void setDiseaseType2(String diseaseType2) {
        this.diseaseType2 = diseaseType2;
    }

    public String getDiseaseType3() {
        return diseaseType3;
    }

    public void setDiseaseType3(String diseaseType3) {
        this.diseaseType3 = diseaseType3;
    }

    public String getDiseaseType4() {
        return diseaseType4;
    }

    public void setDiseaseType4(String diseaseType4) {
        this.diseaseType4 = diseaseType4;
    }

    public String getDiseaseType5() {
        return diseaseType5;
    }

    public void setDiseaseType5(String diseaseType5) {
        this.diseaseType5 = diseaseType5;
    }

    public String getDiseaseType6() {
        return diseaseType6;
    }

    public void setDiseaseType6(String diseaseType6) {
        this.diseaseType6 = diseaseType6;
    }

    public String getComplicationName() {
        return complicationName;
    }

    public void setComplicationName(String complicationName) {
        this.complicationName = complicationName;
    }

    public String getComplicationType() {
        return complicationType;
    }

    public void setComplicationType(String complicationType) {
        this.complicationType = complicationType;
    }

    public String getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getDiseaseChildType() {
        return diseaseChildType;
    }

    public void setDiseaseChildType(String diseaseChildType) {
        this.diseaseChildType = diseaseChildType;
    }

    public String getDiseaseRisk() {
        return diseaseRisk;
    }

    public void setDiseaseRisk(String diseaseRisk) {
        this.diseaseRisk = diseaseRisk;
    }

    public String getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(String diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }


}
