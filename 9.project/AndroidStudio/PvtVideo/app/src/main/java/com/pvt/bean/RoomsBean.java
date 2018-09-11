package com.pvt.bean;

import java.io.Serializable;
import java.util.List;

public class RoomsBean implements Serializable {
    private String code;
    private String type;
    private String message;
    private List<RoomBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RoomBean> getResult() {
        return result;
    }

    public void setResult(List<RoomBean> result) {
        this.result = result;
    }

    public static class RoomBean implements Serializable {
        /*{
            "roomId": "559712",
                "no": "191002",
                "title": "",
                "nicename": "·圆子·",
                "playUrl": "",
                "quantity": 38702,
                "imageThumb": "http://o.qingsonghr.com/public/attachment/201807/20/18/origin/1532053950191002.jpg?x-oss-process=image/resize,m_mfit,h_150,w_150",
                "image": "http://o.qingsonghr.com/public/attachment/201807/20/18/origin/1532053950191002.jpg?x-oss-process=image/resize,m_mfit,h_260,w_260",
                "groupId": "",
                "appId": "",
                "livePlatformId": "63b3ffc1ec5c4e97b9ae83a6d211105b"
        },*/
        private String roomId;
        private String no;
        private String title;
        private String nicename;
        private String playUrl;
        private String quantity;
        private String imageThumb;
        private String image;
        private String groupId;
        private String appId;
        private String livePlatformId;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNicename() {
            return nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getImageThumb() {
            return imageThumb;
        }

        public void setImageThumb(String imageThumb) {
            this.imageThumb = imageThumb;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
    }
}
