package com.pvt.pvtvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.db.bean.PvtDB;
import com.db.util.DButil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.pvt.bean.CodeBean;
import com.pvt.bean.LoginBean;
import com.pvt.bean.RegBean;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.pvt.util.ImageUtil;
import com.pvt.util.RandomUtil;

import org.litepal.LitePal;

import cz.msebera.android.httpclient.Header;

public class ActivityRegistered extends AppCompatActivity implements View.OnClickListener {
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
        HttpUtils.post(HttpUtils.USER_CODE, new RequestParams(), new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                CodeBean cb = GsonUtils.parseJson(responseString, CodeBean.class);
                RegImg.setImageBitmap(ImageUtil.strToBase64(cb.getResult().getImg()));
                new PvtDB("token", cb.getResult().getToken()).save();
            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });
    }

    //注册
    private void register() {
        /**app=nice&countryCode=86&invitationCode=AAA111&
         password=oqhwg60326965&
         token=522fa7285860455aa687be5a74b9001f&username=14747939305&validateCode=4ya1*/

        new PvtDB("password", RandomUtil.getPassword()).save();

        RequestParams rp = new RequestParams();
        rp.put("app", "nice");
        rp.put("countryCode", "86");
        rp.put("invitationCode", "AAA111");

        rp.put("password", DButil.PvtDBvaule("password"));
        rp.put("token", DButil.PvtDBvaule("token"));
        rp.put("username", RandomUtil.getTel());
        rp.put("validateCode", RegEdit.getText().toString().trim());


        HttpUtils.post(HttpUtils.USER_REG, rp, new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                RegBean rb = GsonUtils.parseJson(responseString, RegBean.class);

                new PvtDB("userId", rb.getResult().getId()).save();
                new PvtDB("username", rb.getResult().getUsername()).save();

                login();
            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });
    }

    //登陆
    private void login() {
        /**app=nice&countryCode=86&password=oqhwg60326965&username=14747939305*/
        RequestParams rp = new RequestParams();
        rp.put("app", "nice");
        rp.put("countryCode", "86");

        rp.put("password", DButil.PvtDBvaule("password"));//3raixo
        rp.put("username", DButil.PvtDBvaule("username"));//15205171203

        HttpUtils.post(HttpUtils.USER_LOGIN, rp, new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                LoginBean lb = GsonUtils.parseJson(responseString, LoginBean.class);

                new PvtDB("token", lb.getResult().getToken()).save();

                Toast.makeText(ActivityRegistered.this, lb.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
