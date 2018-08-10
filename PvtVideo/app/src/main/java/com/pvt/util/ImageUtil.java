package com.pvt.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import com.pvt.base.PvtVideoApplication;
import com.pvt.pvtvideo.ActivityRegistered;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {


    /**
     * String转Base64图片
     */
    public static Bitmap strToBase64(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 保存bitmap到本地
     */
    public static void saveBitmap(Bitmap bitmap, String fileName) {

        String sdCardDir = Environment.getExternalStorageDirectory() + PvtVideoApplication.CACHE + "/";
        File dirFile = new File(sdCardDir);  //目录转化成文件夹
        if (!dirFile.exists()) {                //如果不存在，那就建立这个文件夹
            dirFile.mkdirs();
        }                            //文件夹有啦，就可以保存图片啦
        File file = new File(sdCardDir, fileName);// 在SDcard的目录下创建图片

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            System.out.println("_________保存到____sd______指定目录文件夹下________成功____________");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("_________保存到____sd______指定目录文件夹下________失败____________");
        }

    }

}
