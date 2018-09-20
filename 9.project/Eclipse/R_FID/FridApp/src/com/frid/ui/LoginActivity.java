package com.frid.ui;
import java.util.List;

import cn.pedant.SweetAlert.CallDialog;

import com.frid.data.AppData;
import com.frid.data.FridApplication;
import com.frid.data.TestMsg;
import com.frid.db.DBHelper;
import com.frid.db.TDao;
import com.frid.fridapp.R;
import com.frid.pojo.DBLog;
import com.frid.pojo.GsonUser;
import com.frid.tool.ASHttp;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.view.RSActivity;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class LoginActivity extends RSActivity implements OnClickListener,OnCheckedChangeListener{
	private EditText UserName,PassWord;
	private CheckBox LoainSavePass,AILogin;
	@Override   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ini(); 
	}
	private void ini() {
		findViewById(R.id.LoginRunBut).setOnClickListener(this);

		UserName =(EditText) findViewById(R.id.UserName);
		PassWord =(EditText) findViewById(R.id.PassWord);
		LoainSavePass = (CheckBox) findViewById(R.id.LoainSavePass);  
		AILogin = (CheckBox) findViewById(R.id.AILogin);  
		LoainSavePass.setOnCheckedChangeListener(this);
		AILogin.setOnCheckedChangeListener(this);

		//判断记住密码多选框的状态  
		if(FridApplication.sp.getBoolean("ISCHECK", false))  
		{  
			//设置默认是记录密码状态  
			LoainSavePass.setChecked(true);  
			UserName.setText(FridApplication.userName.trim());
			PassWord.setText(FridApplication.passWord.trim());

			TestMsg.loginTest(UserName, PassWord);

			//判断自动登录多选框状态  
			if(FridApplication.sp.getBoolean("AUTO_ISCHECK", false))  
			{   
				//设置默认是自动登录状态  
				AILogin.setChecked(true);  
				//跳转界面  
				Loading("登录中..");
				loginService();
				/**
				 * 登录Service操作
				 * */
			}else AILogin.setChecked(false);  
		}else LoainSavePass.setChecked(false); 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) { 
		case R.id.LoginRunBut: 
			if(UserName.getText().toString()!=null&&
			!UserName.getText().toString().equals("")&&
			PassWord.getText().toString()!=null&&
			!PassWord.getText().toString().equals("")){
				Loading("登录中..");

				loginService();
				/**
				 * 登录Service操作
				 * */
			}else 
				Toast("账号或密码不可为空.");
			break;
		}
	}
	private void loginService() {
		ASHttp.Login(getApplicationContext(), UserName.getText().toString(),PassWord.getText().toString(), new AsyncHttp(){
			@Override
			public void onResult(boolean b, String msg) {
				// 0仓管(入库 盘点 核对 设置)    1送货（保险箱+设置）
				super.onResult(b, msg);
				if(b){
					/**  #### 更多逻辑后面补充,默认成功！*/

					Gson g = new Gson();
					final GsonUser gu = g.fromJson(msg, GsonUser.class);
					
//					int i = gu.getPermissions().length;

					if(gu.getResponseCode().equals("0000"))
						LoadingClose("登录成功!",true, new CallDialog() {
							@Override
							public void closeDialog() {
								super.closeDialog();
								/**完全成功操作*/
								FridApplication.insertData(
										new String[]{"userName",UserName.getText().toString().trim()},
										new String[]{"passWord",PassWord.getText().toString().trim()},
										new String[]{"token",gu.getToken()}
										);
								FridApplication.insertIdentity("Identity",
										gu.getPermissions().length==0?AppData.permissions:
											Integer.parseInt(gu.getPermissions()[0]));
								startActivity(new Intent(LoginActivity.this,Main.class));
								finish();
							}
						});
					else LoadingClose(gu.getResponseMessage(),false,null);
				}else LoadingClose("网络异常.",false,null);
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		if (LoainSavePass.isChecked()) {  
			System.out.println("记住密码已选中");  
			FridApplication.sp.edit().putBoolean("ISCHECK", true).commit();  
		}else {  
			System.out.println("记住密码没有选中");  
			FridApplication.sp.edit().putBoolean("ISCHECK", false).commit();  
		} 
		if (AILogin.isChecked()) {  
			System.out.println("自动登录已选中");  
			FridApplication.sp.edit().putBoolean("AUTO_ISCHECK", true).commit();  
			//设置默认是记录密码状态  
			LoainSavePass.setChecked(true);  
			System.out.println("记住密码已选中");  
			FridApplication.sp.edit().putBoolean("ISCHECK", true).commit();  
		}else {  
			System.out.println("自动登录没有选中");  
			FridApplication.sp.edit().putBoolean("AUTO_ISCHECK", false).commit();  
		}  
	}
}