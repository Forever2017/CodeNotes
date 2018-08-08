package com.pvt.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtils {
    //http://api.yy2025.com/api/zhibo/flatbed/getList
    private static final String HOST = "http://api.yy2025.com/api/zhibo";

    public static final String HOME_LIST = HOST+"/flatbed/getList";
    public static final String ROOM_LIST = HOST+"/flatbedAnchor/list";
    public static final String ROOM_INFO = HOST+"/flatbedAnchor/info";

    public static final String USER_CODE = HOST+"/member/getValidateCode";//验证码
    public static final String USER_REG = HOST+"/member/register";//注册
    public static final String USER_LOGIN = HOST+"/member/login";//登陆



    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }



}