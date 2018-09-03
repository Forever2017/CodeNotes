package com.pvt.pvtvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.db.bean.PvtDB;
import com.db.util.DButil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.RequestParams;
import com.pvt.base.PvtVideoApplication;
import com.pvt.bean.CodeBean;
import com.pvt.bean.LoginBean;
import com.pvt.bean.RegBean;
import com.pvt.util.FileUtil;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.pvt.util.ImageUtil;
import com.pvt.util.RandomUtil;
import java.io.File;

public class ActivityRegistered extends AppCompatActivity implements View.OnClickListener {
    private SimpleDraweeView RegImg;
    private EditText RegEdit;
    private Button RegOk,Fuck;
    private TextView OcrState;

    private GeneralBasicParams param;

    //验证码地址
    private String AUTH_CODE = "auth_code.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        param = new GeneralBasicParams();

        OcrState = findViewById(R.id.OcrState);
        RegImg = findViewById(R.id.RegImg);
        RegEdit = findViewById(R.id.RegEdit);
        RegOk = findViewById(R.id.RegOk);
        Fuck = findViewById(R.id.Fuck);
        RegImg.setOnClickListener(this);
        RegOk.setOnClickListener(this);
        Fuck.setOnClickListener(this);


        getValidateCode();

    }


    //验证码
    private void getValidateCode() {
        HttpUtils.post(HttpUtils.USER_CODE, new RequestParams(), new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                CodeBean cb = GsonUtils.parseJson(responseString, CodeBean.class);
//              RegImg.setImageBitmap(ImageUtil.strToBase64(imgTemp));
                RegImg.setImageURI(cb.getResult().getImg());
                //存储为jpg文件
                ImageUtil.saveBitmap(ImageUtil.strToBase64(cb.getResult().getImg()),AUTH_CODE);

                OcrState.setText("OCR识别中..");
                //验证码识别
                ocrNumber(FileUtil.sdFile() + PvtVideoApplication.CACHE + "/" + AUTH_CODE);

                //保存数据库
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
                finish();
            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });
    }

    //验证码识别
    private void ocrNumber(String img) {
        // 网络图片文字识别参数设置
        param.setImageFile(new File(img));
        // 调用通用文字识别服务
        OCR.getInstance(this).recognizeWebimage(param, new OnResultListener<GeneralResult>() {
            @Override
            public void onResult(GeneralResult result) {
                Log.e("PvtVideo","OCR识别失败.");

                if(result!=null&&result.getWordsResultNumber() == 1){
                    OcrState.setText("OCR识别成功.");
                    RegEdit.setText( result.getWordList().get(0).getWords().toString());
                }else{
                    //识别失败
                    OcrState.setText("OCR识别失败,点击验证码重新识别~(百度OCR成功率真低..)");
                }

            }

            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError对象
                Log.e("PvtVideo","OCR识别失败.");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RegImg:
                RegEdit.setText("");
                getValidateCode();
                break;

            case R.id.RegOk:
                register();
                break;

            case R.id.Fuck:
                RegEdit.setText("");
                break;


            default:
        }

    }


}
