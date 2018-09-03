package com.pvt.util;

import android.content.Context;
import android.os.Environment;

import com.pvt.base.PvtVideoApplication;

import java.io.File;

public class FileUtil {

    public File FileCreate(Context context) {
        File cacheDir;
        // 如果有SD卡则在SD卡中建一个LazyList的目录存放缓存的图片
        // 没有SD卡就放在系统的缓存目录中
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory(),
                    PvtVideoApplication.CACHE);
        else
            cacheDir = context.getCacheDir();

        if (!cacheDir.exists())
            cacheDir.mkdirs();

        return cacheDir;
    }


    /**获取存储路径*/
    public static String sdFile(){

        return Environment.getExternalStorageDirectory().getPath();

    }

}
