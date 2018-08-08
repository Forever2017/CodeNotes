package com.pvt.pvtvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.db.core.Core;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.pvt.adapter.AdapterRoomList;
import com.pvt.bean.CodeBean;
import com.pvt.bean.LoginBean;
import com.pvt.bean.RegBean;
import com.pvt.bean.RoomsBean;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.pvt.util.MoreUtil;
import com.pvt.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ActivityRegistered extends AppCompatActivity implements View.OnClickListener{
    private ImageView RegImg;
    private EditText RegEdit;
    private Button RegOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        RegImg = findViewById(R.id.RegImg);
        RegEdit = findViewById(R.id.RegEdit);
        RegOk = findViewById(R.id.RegOk);
        RegImg.setOnClickListener(this);
        RegOk.setOnClickListener(this);
        getValidateCode();

    }
    //验证码
    private void getValidateCode() {
        HttpUtils.post(HttpUtils.USER_CODE, new RequestParams(), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {

                    CodeBean cb =  GsonUtils.parseJson(responseString, CodeBean.class);

                    RegImg.setImageBitmap(MoreUtil.stringToBitmap(cb.getResult().getImg()));

                    Core.userDb.setToken(cb.getResult().getToken());
                }
            }
        });
    }

    //注册
    private void register() {
        /**app=nice&countryCode=86&invitationCode=AAA111&
        password=oqhwg60326965&
        token=522fa7285860455aa687be5a74b9001f&username=14747939305&validateCode=4ya1*/

        Core.userDb.setPassword(RandomUtil.getPassword());

        RequestParams rp = new RequestParams();
        rp.put("app","nice");
        rp.put("countryCode","86");
        rp.put("invitationCode","AAA111");
        rp.put("password",Core.userDb.getPassword());
        rp.put("token",Core.userDb.getToken());
        rp.put("username",RandomUtil.getTel());
        rp.put("validateCode",RegEdit.getText().toString().trim());

        HttpUtils.post(HttpUtils.USER_REG, rp, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {

                    RegBean rb =  GsonUtils.parseJson(responseString, RegBean.class);

                    Core.userDb.setId(rb.getResult().getId());
                    Core.userDb.setUsername(rb.getResult().getUsername());

                    login();
                }
            }
        });
    }
    //登陆
    private void login() {
        /**app=nice&countryCode=86&password=oqhwg60326965&username=14747939305*/
        RequestParams rp = new RequestParams();
        rp.put("app","nice");
        rp.put("countryCode","86");

        rp.put("password",Core.userDb.getPassword());//3raixo
        rp.put("username",Core.userDb.getUsername());//15205171203

        HttpUtils.post(HttpUtils.USER_LOGIN, rp, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {

                    LoginBean lb =  GsonUtils.parseJson(responseString, LoginBean.class);

                    Core.userDb.setToken(lb.getResult().getToken()!=null?lb.getResult().getToken():"");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.RegImg:
                getValidateCode();
            break;

            case R.id.RegOk:
                register();
                break;


            default:
        }

    }


}
