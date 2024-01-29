package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationAllInfo {
    private String studyTimeSum;
    private String studyCourseSum;
    private List<EducationInfo> indexList;

    public String getStudyTimeSum() {
        return studyTimeSum;
    }

    public void setStudyTimeSum(String studyTimeSum) {
        this.studyTimeSum = studyTimeSum;
    }

    public String getStudyCourseSum() {
        return studyCourseSum;
    }

    public void setStudyCourseSum(String studyCourseSum) {
        this.studyCourseSum = studyCourseSum;
    }

    public List<EducationInfo> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<EducationInfo> indexList) {
        this.indexList = indexList;
    }
}
