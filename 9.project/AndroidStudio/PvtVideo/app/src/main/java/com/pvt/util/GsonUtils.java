package com.pvt.util;

import com.google.gson.Gson;
public class GsonUtils {


    public static <T> T parseJson(String s, Class<T> clz) {
        Gson gson = new Gson();
        T result = null;
        try {
            result = gson.fromJson(s, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
