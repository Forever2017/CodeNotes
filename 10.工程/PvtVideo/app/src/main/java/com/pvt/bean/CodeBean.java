package com.pvt.bean;

import java.io.Serializable;

public class CodeBean extends BaseBean {
    private UserCodeBean result;

    public UserCodeBean getResult() {
        return result;
    }

    public void setResult(UserCodeBean result) {
        this.result = result;
    }

    public static class UserCodeBean implements Serializable {
        private String img;
        private String token;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
