package com.db.util;

import com.db.bean.PvtDB;

import org.litepal.LitePal;

public class DButil {


    public static String PvtDBvaule(String name){

        PvtDB temp = LitePal.where("name = ?", name).findFirst(PvtDB.class);

        if (temp != null) return temp.getValue();
        else return null;

    }


}
