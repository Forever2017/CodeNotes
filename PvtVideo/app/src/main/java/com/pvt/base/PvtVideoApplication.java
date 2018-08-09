package com.pvt.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.litepal.LitePal;

public class PvtVideoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Fresco.initialize(this);


    }

}