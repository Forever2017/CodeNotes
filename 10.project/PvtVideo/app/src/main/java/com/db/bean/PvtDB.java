package com.db.bean;

import com.db.util.DButil;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/*private String token;
private String userId;
private String username;//手机号
private String password;*/

public class PvtDB extends LitePalSupport implements Serializable {
    @Column(unique = true) //唯一
    private String name;

    private String value;

    public PvtDB() {
    }

    public PvtDB(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean save() {
        if (LitePal.where("name = ?", this.getName()).find(this.getClass()).size() > 0) {
            //更新
            return this.updateAll("name = ?", this.getName()) == 0 ? false : true;
        } else
            return super.save();
    }

}
