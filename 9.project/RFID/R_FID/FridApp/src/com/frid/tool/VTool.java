package com.frid.tool;

import java.util.ArrayList;
import java.util.List;

import com.frid.adapter.MListAdapter;
import com.frid.data.FridApplication;
import com.frid.data.TestMsg;
import com.frid.fridapp.R;
import com.frid.pojo.BillList;
import com.frid.pojo.GsonItem;
import com.frid.pojo.GsonStock;
import com.frid.tool.ASHttp.AsyncHttp;
import com.google.gson.Gson;
import com.polling.PollingService;
import com.polling.PollingUtils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import device.frid.Device;


/**
 * 自定义View集合..
 * */
public class VTool {

	private static SweetAlertDialog Interaction;//交互对话框.

	/** 对话框
	 * String title, 对话框标题
	 * String content, 对话框内容
	 * String noBut,  取消按钮问题
	 * String yesBut  确认按钮文字
	 * CallRActivity cra 回调
	 * */
	public static void Interaction(Context context,int resourceId,String title,String content,String noBut,String yesBut,final CallbackVT cra) {
		Interaction = new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
		Interaction(title,content,noBut,yesBut,cra);
		Interaction.setWarningImage(resourceId);
	}
	public static void Interaction(Context context,String title,String content,String noBut,String yesBut,final CallbackVT cra) {
		Interaction = new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
		Interaction(title,content,noBut,yesBut,cra);
	}		
	public static void Interaction(String title,String content,String noBut,String yesBut,final CallbackVT cra) {
		Interaction.setTitleText(title);//大标题
		Interaction.setContentText(content);//内容
		Interaction.setCancelText(noBut);//取消按钮
		Interaction.setConfirmText(yesBut);//确认按钮
		Interaction.showCancelButton(true);
		Interaction.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sDialog) {
				//点击取消
				Interaction.dismissWithAnimation();
				cra.InteractionNo();
			}
		});
		Interaction.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sDialog) { 
				//点击确认
				Interaction.dismissWithAnimation();
				cra.InteractionYes();
			}
		});
		Interaction.show();
	}

	//不通用  选择核对单
	public static void querySttingDialog(Context context,final CallbackVT sm) {
		//新建自己风格的dialog
		final Dialog ADDdialog = new Dialog(context,R.style.MyDialog);
		ADDdialog.setContentView(R.layout.dialog_query_setting);
		ADDdialog.show();

		ListView listview = (ListView) ADDdialog.findViewById(R.id.dialog_query_setting_list);
		Button dialog_login_setting_close = (Button) ADDdialog.findViewById(R.id.dialog_query_setting_close);

		final List<GsonItem> list = new ArrayList<GsonItem>();
		final MListAdapter ma = new MListAdapter(context, list,"query");
		listview.setAdapter(ma);

		ASHttp.QueryList(context,new AsyncHttp() {
			public void onResult(boolean b, String msg) {
				msg = TestMsg.updateMSG("QueryList", msg);

				if(b){
					Gson g = new Gson();
					GsonStock gc = g.fromJson(msg, GsonStock.class);
					if(gc.getResponseCode().equals("0000")){/**获取数据成功*/

						list.clear();
						list.addAll(gc.getList());
						ma.notifyDataSetChanged();

					}
				}
			};

		} );

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int i,
					long arg3) {
				sm.ReturnData(list.get(i).getId());
				ADDdialog.dismiss();
			}
		});
		dialog_login_setting_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ADDdialog.dismiss();
			}
		});
	}

	//不通用  输入运单号
	public static void inputDialog(final Context context,final List<String> blist, final CallbackVT sm) {
		//新建自己风格的dialog
		final Dialog ADDdialog = new Dialog(context,R.style.MyDialog);
		ADDdialog.setContentView(R.layout.dialog_input);
		ADDdialog.show();

		Button dialog_input_close = (Button) ADDdialog.findViewById(R.id.dialog_input_close);
		final EditText dialog_input_edit = (EditText) ADDdialog.findViewById(R.id.dialog_input_edit);
		final Button dialog_input_but = (Button) ADDdialog.findViewById(R.id.dialog_input_but);

		final TextView dialog_input_prompt = (TextView) ADDdialog.findViewById(R.id.dialog_input_prompt);
		final LinearLayout dialog_input_Linear = (LinearLayout) ADDdialog.findViewById(R.id.dialog_input_Linear);
		Button dialog_input_ok = (Button) ADDdialog.findViewById(R.id.dialog_input_ok);
		Button dialog_input_no = (Button) ADDdialog.findViewById(R.id.dialog_input_no);

		dialog_input_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ADDdialog.dismiss();
			}
		});

		dialog_input_but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(dialog_input_edit.getText().toString()!=null&&!dialog_input_edit.getText().toString().equals("")){
					String temp = dialog_input_edit.getText().toString().trim();
					if(blist==null||blist.size()==0||!blist.contains(temp)){
						//匹配不到订单号
						dialog_input_but.setVisibility(View.GONE);

						dialog_input_prompt.setVisibility(View.VISIBLE);
						dialog_input_Linear.setVisibility(View.VISIBLE);
					}else{
						//匹配到订单号
						sm.ReturnData(temp);
						ADDdialog.dismiss();
					}
				}else 
					Toast.makeText(context.getApplicationContext(), "请输入运单号！", 0).show();
			}
		});

		dialog_input_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sm.ReturnData(dialog_input_edit.getText().toString().trim());
				ADDdialog.dismiss();
			}
		});

		dialog_input_no.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog_input_prompt.setVisibility(View.GONE);
				dialog_input_Linear.setVisibility(View.GONE);
				dialog_input_but.setVisibility(View.VISIBLE);
			}
		});

	}

	private static int TEMP_INT = 0;
	//频率选择
	public static void powerDialog(final Context context) {
		//新建自己风格的dialog
		final Dialog ADDdialog = new Dialog(context,R.style.MyDialog);
		ADDdialog.setContentView(R.layout.dialog_power);
		ADDdialog.show();

		final Button dialog_power_close = (Button) ADDdialog.findViewById(R.id.dialog_power_close);
		final Button dialog_power_but = (Button) ADDdialog.findViewById(R.id.dialog_power_but);
		final SeekBar dialog_power_seekBar = (SeekBar) ADDdialog.findViewById(R.id.dialog_power_seekBar);
		final TextView dialog_power_text = (TextView) ADDdialog.findViewById(R.id.dialog_power_text);
		
		dialog_power_text.setText("当前频率："+FridApplication.FRID_POWER);
		dialog_power_seekBar.setProgress(FridApplication.FRID_POWER-5);
		TEMP_INT = FridApplication.FRID_POWER;

		dialog_power_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ADDdialog.dismiss();
			}
		});

		dialog_power_but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//真实设置
				if(new Device(context).setPower(TEMP_INT)){
					FridApplication.FRID_POWER = TEMP_INT;
					FridApplication.insertIdentity("FRID_POWER", TEMP_INT);
				}else{
					Toast.makeText(context, "设置失败.", 0).show();
				}
				ADDdialog.dismiss();
			}
		});

		dialog_power_seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// 停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {}
			// 开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {}
			// 数值改变
			@Override
			public void onProgressChanged(SeekBar arg0, int i, boolean arg2) {
				//if(i<5) i = 5;
				i = i+5;
				dialog_power_text.setText("当前频率："+i);
				TEMP_INT = i;
			} 
		});
	}
	//同步周期
	public static void pollingDialog(final Context context) {
		//新建自己风格的dialog
		final Dialog ADDdialog = new Dialog(context,R.style.MyDialog);
		ADDdialog.setContentView(R.layout.dialog_polling);
		ADDdialog.show();

		final Button dialog_polling_close = (Button) ADDdialog.findViewById(R.id.dialog_polling_close);
		final Button dialog_polling_but = (Button) ADDdialog.findViewById(R.id.dialog_polling_but);
		final SeekBar dialog_polling_seekBar = (SeekBar) ADDdialog.findViewById(R.id.dialog_polling_seekBar);
		final TextView dialog_polling_text = (TextView) ADDdialog.findViewById(R.id.dialog_polling_text);
		dialog_polling_text.setText("同步间隔时间："+FridApplication.POLLING_TIME/60+" 分钟");
		dialog_polling_seekBar.setProgress(FridApplication.POLLING_TIME/60);
		TEMP_INT = FridApplication.POLLING_TIME;

		dialog_polling_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ADDdialog.dismiss();
			}
		});
 
		dialog_polling_but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//真实设置
				FridApplication.POLLING_TIME = TEMP_INT;
				FridApplication.insertIdentity("POLLING_TIME", TEMP_INT);
				//打开轮询服务
				PollingUtils.startPollingService(context, FridApplication.POLLING_TIME, PollingService.class, PollingService.ACTION);
				ADDdialog.dismiss();
			}
		});

		dialog_polling_seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// 停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {}
			// 开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {}
			// 数值改变
			@Override
			public void onProgressChanged(SeekBar arg0, int i, boolean arg2) {
				if(i==0) i = 1;
				dialog_polling_text.setText("同步间隔时间："+i+" 分钟");
				TEMP_INT = i*60;
			}
		});
	}





	/**回调方法*/
	public static abstract class CallbackVT{
		/**对话框点击确认按钮*/
		public void InteractionYes(){}
		/**对话框点击取消按钮*/
		public void InteractionNo(){}
		/**返回值*/
		public void ReturnData(String msg){}
	}
}
