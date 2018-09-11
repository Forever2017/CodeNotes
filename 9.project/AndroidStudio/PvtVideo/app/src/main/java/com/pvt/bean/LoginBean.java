package com.pvt.bean;

import java.io.Serializable;

public class LoginBean extends BaseBean {
    private UserRegBean result;

    public UserRegBean getResult() {
        return result;
    }

    public void setResult(UserRegBean result) {
        this.result = result;
    }

    public static class UserRegBean implements Serializable {
        private String token;
        private User user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public static class User implements Serializable {
            private String id;
            private String username;
            private String vipExpiryTime;
            private String nickName;
            private String invitationCode;
            private String contact;
            private String monthUrl;
            private String quarterUrl;
            private String yearUrl;
            private String foreverUrl;

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

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
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
        }
    }
}
