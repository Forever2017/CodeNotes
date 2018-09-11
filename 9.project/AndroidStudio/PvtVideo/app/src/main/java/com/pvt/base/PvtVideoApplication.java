package com.pvt.base;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.pvt.util.FileUtil;

import org.litepal.LitePal;

import java.io.File;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public class PvtVideoApplication extends Application {
    //  public static final String SP = "com.pvt.video";
    public static final String PVT_FILE = "/PvtVideo";//存储数据文件夹
    public static final String CACHE = PVT_FILE + "/cache";//图片缓存地址

    @Override
    public void onCreate() {
        super.onCreate();

        LitePal.initialize(this);

        //Fresco.initialize(this);
        frescoConfig();

        ocrInt();
    }




    private void ocrInt() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String token = result.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError子类SDKError对象
            }
        }, getApplicationContext(), "ylkicUf8pUvelxPSFZc2dE4X", "RGAXXQhIGvhUbBeRCV5bfDzBrOkAdXmQ");
    }

    private void frescoConfig() {
        Fresco.initialize(this, ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryPath(new FileUtil().FileCreate(getApplicationContext()))// 指定图片缓存路径
                        .build())
                .build());
    }


}