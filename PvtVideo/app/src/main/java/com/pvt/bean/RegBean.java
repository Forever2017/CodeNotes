package com.pvt.bean;

import java.io.Serializable;

public class RegBean extends BaseBean {
    private UserRegBean result;

    public UserRegBean getResult() {
        return result;
    }

    public void setResult(UserRegBean result) {
        this.result = result;
    }

    public static class UserRegBean implements Serializable {
        private String id;
        private String username;
        private String vipExpiryTime;
        private String nickName;
        private String invitationCode;
        private String monthUrl;
        private String quarterUrl;
        private String yearUrl;
        private String foreverUrl;
        private String contact;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getVipExpiryTime() {
            return vipExpiryTime;
        }

        public void setVipExpiryTime(String vipExpiryTime) {
            this.vipExpiryTime = vipExpiryTime;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public String getMonthUrl() {
            return monthUrl;
        }

        public void setMonthUrl(String monthUrl) {
            this.monthUrl = monthUrl;
        }

        public String getQuarterUrl() {
            return quarterUrl;
        }

        public void setQuarterUrl(String quarterUrl) {
            this.quarterUrl = quarterUrl;
        }

        public String getYearUrl() {
            return yearUrl;
        }

        public void setYearUrl(String yearUrl) {
            this.yearUrl = yearUrl;
        }

        public String getForeverUrl() {
            return foreverUrl;
        }

        public void setForeverUrl(String foreverUrl) {
            this.foreverUrl = foreverUrl;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }
}
