package com.pvt.bean;

import java.io.Serializable;
import java.util.List;

public class HomeBean implements Serializable {
    private String code;
    private String type;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        private List<FlatbedsBean> flatbeds;

        public List<FlatbedsBean> getFlatbeds() {
            return flatbeds;
        }

        public void setFlatbeds(List<FlatbedsBean> flatbeds) {
            this.flatbeds = flatbeds;
        }


        public static class FlatbedsBean implements Serializable {
            /*{
                "id":"63b3ffc1ec5c4e97b9ae83a6d211105b",
                    "name":"小清新",
                    "logo":"http://www.yy2025.com/userfiles/1/files/zhibo/flatbed/2018/07/xiaoqingxin_logo.jpg",
                    "quantity":194,    数量
                    "channel":null,    通道
                    "msgType":"1",
                    "livePlugin":"63b3ffc1ec5c4e97b9ae83a6d211105b"
            }*/

            private String id;
            private String name;
            private String logo;
            private String quantity;
            private String channel;
            private String msgType;
            private String livePlugin;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getMsgType() {
                return msgType;
            }

            public void setMsgType(String msgType) {
                this.msgType = msgType;
            }

            public String getLivePlugin() {
                return livePlugin;
            }

            public void setLivePlugin(String livePlugin) {
                this.livePlugin = livePlugin;
            }
        }
    }

}
