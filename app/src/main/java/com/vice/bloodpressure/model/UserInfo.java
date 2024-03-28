package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserInfo implements Serializable {

    /**
     * 头像
     */
    private String avatar;
    /**
     * 糖尿病
     */
    private String diabetesType;
    /**
     * 高血压
     */
    private String hypertensionType;


    private String diseaseName;


    /**
     * 档案ID
     */
    private String archives_id;
    /**
     * 医生ID
     */
    private String doctor_id;
    /**
     * userId
     */
    private String user_id;
    /**
     * 授权码
     */
    private String access_token;
    /**
     * 过期时间
     */
    private String expires_in;
    /**
     * 用户类型(00系统,01用户 02医生 03护士 04主任 05 科室 06医院）
     */
    private String user_type;
    /**
     * 是否完善个人信息  1完善 2未完善
     */
    private String info_status;
    /**
     * 身高
     */
    private String height;
    /**
     * 体重
     */
    private String weight;
    /**
     * bmi数值
     */
    private String bmi;
    /**
     * bmi状态
     * 1 偏瘦
     * 2 正常
     * 3 超重
     * 4 肥胖
     */
    private String bmiStatus;
    /**
     * bmi文字标签
     */
    private String bmiTag;
    /**
     * bmi正常范围文案
     */
    private String bmiNormal;
    /**
     * 收缩压
     */
    private String sbp;
    /**
     * 舒张压
     */
    private String dbp;
    /**
     * 血压测量日期
     */
    private String bpDate;
    /**
     * 血压测量日期
     */
    private String bpStatus;
    /**
     * 血压状态
     * 1 偏低
     * 2 正常
     * 3 偏高
     */
    private String bgValue;
    /**
     * 血糖测量日期
     */
    private String bgDate;
    /**
     * bmi状态
     * 1 偏瘦
     * 2 正常
     * 3 超重
     * 4 肥胖
     */
    private String bgStatus;
    /**
     * 心率
     */
    private String hr;
    /**
     * 心率状态
     * 1 偏低
     * 2 正常
     * 3 偏低
     */
    private String hrStatus;
    /**
     * 心率检测日期
     */
    private String hrDate;
    /**
     * 肾脏病变
     * 1 是
     * 0 否
     */
    private String dn;
    /**
     * 视网膜病变
     */
    private String dr;
    /**
     * 神经病变
     */
    private String dpn;
    /**
     * 下肢血管病变
     */
    private String lead;
    /**
     * 糖尿病足
     */
    private String df;

    /**
     * 设备编号
     */
    private String deviceCode;
    /**
     * 测量时间
     */
    private String addTime;
    /**
     * 型1-8;9糖化血红蛋白
     */
    private String type;


    /**
     * 姓名
     */
    private String nickName;
    /**
     * 电话
     */
    private String tel;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 紧急联系人
     */
    private String emergency;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 抽烟  Y抽烟 N不抽烟
     */
    private String smokes;
    /**
     * 吸烟数量
     */
    private String smokesNum;
    /**
     * 喝酒 Y 是 N 否 3 未知
     */
    private String wine;
    /**
     * 喝酒类型 饮酒类型:1->红酒;2->啤酒;3->白酒; 4 黄酒
     */
    private String wineType;
    /**
     * 喝酒的数量
     */
    private String wineDose;
    /**
     * 1已婚 2未婚 3其他
     */
    private String marital;
    /**
     * 妊娠  Y/N
     */
    private String pregnancy;
    /**
     * 妊娠时间
     */
    private String pregnancyTime;
    /**
     * 是否独居 Y/N
     */
    private String liveAlone;
    /**
     * 文化程度   1:研究生 2:大学本科 3大学专科和专科学院 4中等专业学校5:技工学校6:高中7:初中8:小学9:文盲或半文盲10:未知
     */
    private String education;
    /**
     * 是否卧床  Y/N
     */
    private String bedridden;
    /**
     * 1轻体力 2 中体力 3 重体力
     */
    private String profession;
    /**
     * 医疗支付方式 1:社会医疗保险 2:新型农村合作医疗保险 3:商业保险 4:城镇居民医疗保险 5:公费医疗 6:自费医疗 7:其他
     */
    private String medicalPay;
    /**
     * 就诊卡号
     */
    private String medicalCard;

    /**
     * 家族史
     */
    private List<DiseaseInfo> patientFamily;
    /**
     * 并发症
     */
    private List<DiseaseInfo> complication;
    /**
     * 主要诊断
     */
    private List<DiseaseInfo> mainDiagnosis;
    /**
     * 其他诊断
     */
    private List<DiseaseInfo> otherDiagnosis;
    /**
     * 紧急联系人电话
     */
    private String emergencyMode;
    /**
     * 0 存在 1不存在 判断合并症是不是需要展示
     */
    private String isDiabetesExists;
    /**
     * 疾病类型
     */
    private String diseaseType;
    /**
     * //诊断类型 1主要 2其他
     */
    private String diagnosticType;

    /**
     * 个人中心电话号码
     */
    private String phoneNumber;


    /**
     * 有关首页血糖血压bmi模块
     */
    private String iframeUrl;
    /**
     * 总测量次数
     */
    private String takeSum;
    /**
     *偏高次数
     */
    private String takeHeightNum;
    /**
     *正常次数
     */
    private String takeNormalNum;
    /**
     *偏低次数
     */
    private String takeLowNum;
    /**
     *bmi值  状态1偏低 2正常 3偏高
     */
    private String bmiValue;
    /**
     *时间
     */
    private String bmiDate;

    /**
     * true已绑定/false未绑定
     */
    private boolean isBindExternal;

    public boolean isBindExternal() {
        return isBindExternal;
    }

    public void setBindExternal(boolean bindExternal) {
        isBindExternal = bindExternal;
    }

    public String getTakeSum() {
        return takeSum;
    }

    public void setTakeSum(String takeSum) {
        this.takeSum = takeSum;
    }

    public String getTakeHeightNum() {
        return takeHeightNum;
    }

    public void setTakeHeightNum(String takeHeightNum) {
        this.takeHeightNum = takeHeightNum;
    }

    public String getTakeNormalNum() {
        return takeNormalNum;
    }

    public void setTakeNormalNum(String takeNormalNum) {
        this.takeNormalNum = takeNormalNum;
    }

    public String getTakeLowNum() {
        return takeLowNum;
    }

    public void setTakeLowNum(String takeLowNum) {
        this.takeLowNum = takeLowNum;
    }

    public String getBmiValue() {
        return bmiValue;
    }

    public void setBmiValue(String bmiValue) {
        this.bmiValue = bmiValue;
    }

    public String getBmiDate() {
        return bmiDate;
    }

    public void setBmiDate(String bmiDate) {
        this.bmiDate = bmiDate;
    }

    public String getIframeUrl() {
        return iframeUrl;
    }

    public void setIframeUrl(String iframeUrl) {
        this.iframeUrl = iframeUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public List<DiseaseInfo> getPatientFamily() {
        return patientFamily;
    }

    public void setPatientFamily(List<DiseaseInfo> patientFamily) {
        this.patientFamily = patientFamily;
    }

    public String getDiagnosticType() {
        return diagnosticType;
    }

    public void setDiagnosticType(String diagnosticType) {
        this.diagnosticType = diagnosticType;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getIsDiabetesExists() {
        return isDiabetesExists;
    }

    public void setIsDiabetesExists(String isDiabetesExists) {
        this.isDiabetesExists = isDiabetesExists;
    }

    public String getEmergencyMode() {
        return emergencyMode;
    }

    public void setEmergencyMode(String emergencyMode) {
        this.emergencyMode = emergencyMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }


    public String getArchivesId() {
        return archives_id;
    }

    public void setArchivesId(String archivesId) {
        this.archives_id = archivesId;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getInfo_status() {
        return info_status;
    }

    public void setInfo_status(String info_status) {
        this.info_status = info_status;
    }

    public String getArchives_id() {
        return archives_id;
    }

    public void setArchives_id(String archives_id) {
        this.archives_id = archives_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBmiStatus() {
        return bmiStatus;
    }

    public void setBmiStatus(String bmiStatus) {
        this.bmiStatus = bmiStatus;
    }

    public String getBmiTag() {
        return bmiTag;
    }

    public void setBmiTag(String bmiTag) {
        this.bmiTag = bmiTag;
    }

    public String getBmiNormal() {
        return bmiNormal;
    }

    public void setBmiNormal(String bmiNormal) {
        this.bmiNormal = bmiNormal;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    public String getBpDate() {
        return bpDate;
    }

    public void setBpDate(String bpDate) {
        this.bpDate = bpDate;
    }

    public String getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(String bpStatus) {
        this.bpStatus = bpStatus;
    }

    public String getBgValue() {
        return bgValue;
    }

    public void setBgValue(String bgValue) {
        this.bgValue = bgValue;
    }

    public String getBgDate() {
        return bgDate;
    }

    public void setBgDate(String bgDate) {
        this.bgDate = bgDate;
    }

    public String getBgStatus() {
        return bgStatus;
    }

    public void setBgStatus(String bgStatus) {
        this.bgStatus = bgStatus;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
    }

    public String getHrDate() {
        return hrDate;
    }

    public void setHrDate(String hrDate) {
        this.hrDate = hrDate;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getDpn() {
        return dpn;
    }

    public void setDpn(String dpn) {
        this.dpn = dpn;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSmokes() {
        return smokes;
    }

    public void setSmokes(String smokes) {
        this.smokes = smokes;
    }

    public String getSmokesNum() {
        return smokesNum;
    }

    public void setSmokesNum(String smokesNum) {
        this.smokesNum = smokesNum;
    }

    public String getWine() {
        return wine;
    }

    public void setWine(String wine) {
        this.wine = wine;
    }

    public String getWineType() {
        return wineType;
    }

    public void setWineType(String wineType) {
        this.wineType = wineType;
    }

    public String getWineDose() {
        return wineDose;
    }

    public void setWineDose(String wineDose) {
        this.wineDose = wineDose;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getPregnancyTime() {
        return pregnancyTime;
    }

    public void setPregnancyTime(String pregnancyTime) {
        this.pregnancyTime = pregnancyTime;
    }

    public String getLiveAlone() {
        return liveAlone;
    }

    public void setLiveAlone(String liveAlone) {
        this.liveAlone = liveAlone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBedridden() {
        return bedridden;
    }

    public void setBedridden(String bedridden) {
        this.bedridden = bedridden;
    }

    public String getMedicalPay() {
        return medicalPay;
    }

    public void setMedicalPay(String medicalPay) {
        this.medicalPay = medicalPay;
    }

    public String getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(String medicalCard) {
        this.medicalCard = medicalCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public List<DiseaseInfo> getComplication() {
        return complication;
    }

    public void setComplication(List<DiseaseInfo> complication) {
        this.complication = complication;
    }

    public List<DiseaseInfo> getMainDiagnosis() {
        return mainDiagnosis;
    }

    public void setMainDiagnosis(List<DiseaseInfo> mainDiagnosis) {
        this.mainDiagnosis = mainDiagnosis;
    }

    public List<DiseaseInfo> getOtherDiagnosis() {
        return otherDiagnosis;
    }

    public void setOtherDiagnosis(List<DiseaseInfo> otherDiagnosis) {
        this.otherDiagnosis = otherDiagnosis;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDiabetesType() {
        return diabetesType;
    }

    public void setDiabetesType(String diabetesType) {
        this.diabetesType = diabetesType;
    }

    public String getHypertensionType() {
        return hypertensionType;
    }

    public void setHypertensionType(String hypertensionType) {
        this.hypertensionType = hypertensionType;
    }
}
