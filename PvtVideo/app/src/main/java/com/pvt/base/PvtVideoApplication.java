package com.pvt.base;

import android.app.Application;
import org.litepal.LitePal;

public class PvtVideoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);


    }

}