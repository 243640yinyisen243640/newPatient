package com.vice.bloodpressure.basemodel;


/**
 * 类描述：
 * 类传参：
 *
 * @author android.yys
 * @date 2021/1/16
 */
public class Event {
    public static class FriendshipEvent {
        //用户关系  isFollow 返回的是 0：关注，1：粉丝，2：拉黑，3：互关，4：没关系
        private String isFollow;

        public FriendshipEvent(String isFollow) {
            this.isFollow = isFollow;
        }
    }

    public static class RongTotalUnreadEvent {
        public RongTotalUnreadEvent() {
        }
    }

    public static class GroupReportRemoveEvent {
        private String groupID;

        public GroupReportRemoveEvent(String groupID) {
            this.groupID = groupID;
        }

        public String getGroupID() {
            return groupID;
        }

        public void setGroupID(String groupID) {
            this.groupID = groupID;
        }
    }

    public static class GroupJoinEvent {
        private String groupID;

        public GroupJoinEvent(String groupID) {
            this.groupID = groupID;
        }

        public String getGroupID() {
            return groupID;
        }

        public void setGroupID(String groupID) {
            this.groupID = groupID;
        }
    }

    public static class GroupExitOrDisbandEvent {
        private String groupID;

        public GroupExitOrDisbandEvent(String groupID) {
            this.groupID = groupID;
        }

        public String getGroupID() {
            return groupID;
        }

        public void setGroupID(String groupID) {
            this.groupID = groupID;
        }
    }

    public static class DeleteAddressEvent {
        private String addressID;

        public DeleteAddressEvent(String addressID) {
            this.addressID = addressID;
        }

        public String getAddressID() {
            return addressID;
        }

        public void setAddressID(String addressID) {
            this.addressID = addressID;
        }
    }


    public static class PhoneEvent {
        private String CALL_STATE_RINGING = "1";

        public PhoneEvent(String CALL_STATE_RINGING) {
            this.CALL_STATE_RINGING = CALL_STATE_RINGING;
        }

        public String getCALL_STATE_RINGING() {
            return CALL_STATE_RINGING;
        }

        public void setCALL_STATE_RINGING(String CALL_STATE_RINGING) {
            this.CALL_STATE_RINGING = CALL_STATE_RINGING;
        }

    }

    public static class SystemEvent {
        private String logoID;

        public SystemEvent(String logoID) {
            this.logoID = logoID;
        }

        public String getLogoID() {
            return logoID;
        }

        public void setLogoID(String logoID) {
            this.logoID = logoID;
        }
    }

    public static class RefreshEvent {
        private boolean isRefresh;

        public RefreshEvent(boolean isRefresh) {
            this.isRefresh = isRefresh;
        }

        public boolean isRefresh() {
            return isRefresh;
        }

        public void setRefresh(boolean refresh) {
            isRefresh = refresh;
        }
    }
}