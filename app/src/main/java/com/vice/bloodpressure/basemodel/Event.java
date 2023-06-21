package com.vice.bloodpressure.basemodel;


/**
 * 类描述：
 * 类传参：
 *
 * @author android.yys
 * @date 2021/1/16
 */
public class Event {



    public static class DoctorUnBreakOrBreakEvent {
        private String doctorID;

        public DoctorUnBreakOrBreakEvent(String doctorID) {
            this.doctorID = doctorID;
        }

        public String getDoctorID() {
            return doctorID;
        }

        public void setDoctorID(String doctorID) {
            this.doctorID = doctorID;
        }
    }

}