package com.pvt.bean;

import java.io.Serializable;

public class RoomInfoBean extends BaseBean {
    private RoomBean result;

    public RoomBean getResult() {
        return result;
    }

    public void setResult(RoomBean result) {
        this.result = result;
    }

    public static class RoomBean implements Serializable {
        private String no;
        private String image;
        private String quantity;
        private String is_follow;
        private String groupId;
        private String appId;
        private String livePlatformId;
        private String nicename;
        private String imageThumb;
        private String title;
        private String roomId;
        private String playUrl;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getLivePlatformId() {
            return livePlatformId;
        }

        public void setLivePlatformId(String livePlatformId) {
            this.livePlatformId = livePlatformId;
        }

        public String getNicename() {
            return nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public String getImageThumb() {
            return imageThumb;
        }

        public void setImageThumb(String imageThumb) {
            this.imageThumb = imageThumb;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }
    }
}
