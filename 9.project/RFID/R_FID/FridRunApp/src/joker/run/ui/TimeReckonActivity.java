package joker.run.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import device.frid.Device;
import joker.run.adapter.ResultAdapter;
import joker.run.adapter.TimeAdapter;
import joker.run.data.HOST;
import joker.run.sqliteorm.Epc;
import joker.run.sqliteorm.EpcDao;
import joker.run.sqliteorm.RunRecord;
import joker.run.sqliteorm.RunRecordDao;
import joker.run.ui.ThreadEpcTest.EpcResult;

public class TimeReckonActivity extends ActivityJoker implements Chronometer.OnChronometerTickListener, View.OnClickListener {

	private Chronometer ReckonTime;
	private Button ReckonStop, ReckonOperation;
	private TextView ReckonTimeType, ReckonDistance;

	private int current = 0;//计算时间 秒
	public static final int TIME_RECKON = 9527;

	private Device device;

	private EpcDao epcDao;
	private RunRecordDao recDao;

	private List<RunRecord> recList = new ArrayList<RunRecord>();
	private TimeAdapter mAdapter;
	private ListView TimeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_time_reckon);
		init();
		scanFrid();//设备扫描
	}

	public void init() {
		device = new Device(this);
		epcDao = new EpcDao(this);
		recDao = new RunRecordDao(this);

		TimeList = (ListView) findViewById(R.id.TimeList);
		mAdapter =  new TimeAdapter(this, recList);
		TimeList.setAdapter(mAdapter);

		ReckonTime = (Chronometer) findViewById(R.id.ReckonTime);
		ReckonStop = (Button) findViewById(R.id.ReckonStop);
		ReckonOperation = (Button) findViewById(R.id.ReckonOperation);

		ReckonTimeType = (TextView) findViewById(R.id.ReckonTimeType);
		ReckonDistance = (TextView) findViewById(R.id.ReckonDistance);

		ReckonStop.setOnClickListener(this);
		ReckonOperation.setOnClickListener(this);
		ReckonTime.setOnChronometerTickListener(this);

		ReckonTimeType.setText(getResources().getStringArray(R.array.runs)[HOST.RUN_TYPE]);
		ReckonDistance.setText(HOST.RUN_DIS + " " + getResources().getString(R.string.M));

		ReckonTime.start();

	}


	@Override
	public void onChronometerTick(Chronometer chronometer) {
		current++;
		System.out.println(current + "");
		//chronometer.setText(FormatMiss(current));
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.ReckonStop://  停止计时 \ 查看结果
			ReckonTime.stop();
			ReckonOperation.setBackgroundColor(getResources().getColor(R.color.dimgray));
			ReckonOperation.setEnabled(false);
			if (ReckonStop.getText().toString().equals(getResources().getString(R.string.StopTime))) {//停止
				/* 停止所有*/
				ReckonStop.setText(R.string.checkResult);
				stopScan();//停止设备
			} else {//查看
				/* 关闭，传参数..*/
				setResult(TIME_RECKON);
				finish();
			}

			break;
		case R.id.ReckonOperation://  暂停计时 \ 继续计时
			if (ReckonOperation.getText().toString().equals(getResources().getString(R.string.suspended))) {//暂停
				ReckonTime.stop();
				/*rfid 暂停*/
				stopScan();//停止设备
				ReckonOperation.setText(R.string.Continue);
			} else {//继续
				ReckonTime.start();
				/*rfid 继续*/
				scanFrid();//设备扫描
				ReckonOperation.setText(R.string.suspended);
			}
			break;
		}
	}


	/**
	 * 真实扫描
	 */
	private void scanFrid() {
		/*device.startSearch(new Device.LoopEpc() {
			@Override
			public void ReturnEpc(String epc) {
				super.ReturnEpc(epc);
				Log.e("EPC扫描结果", epc);
				device.biBi(true);

				timeOperation(epc);
			}
		}, false);//是否去除重复*/

		//测试方法
		new ThreadEpcTest(new EpcResult() {
			@Override
			public void onResult(String epc) {
				super.onResult(epc);
				System.out.println("模拟EPC = "+epc);
				timeOperation(epc);
			}
		});
	}

	/**
	 * 真实停止
	 */
	private void stopScan() {
		//真实停止扫描
		device.biBi(false);
		device.stopSearch();
	}


	//计时操作
	private void timeOperation(String epc) {
		//1.检查是否外部跑者
		Epc epcBean = epcDao.queryEpc(epc);
		if(epcBean!=null){  

		}else{//外部跑者
			//主要用来做，是否允许外部跑者，如果不允许，直接return !!测试先全部允许
			//			return;
			epcBean = new Epc();
			epcBean.setEpc(epc);
			epcBean.setName("外部跑者"); 
		}

		//1.检查是否已经有记录
		RunRecord recBean = recDao.queryEpc(epc);
		if(recBean == null) recBean = new RunRecord(epcBean.getEpc(),epcBean.getName());
		isCurrent(recBean);
		recBean.setLast(recBean.getCurrent());//当前圈变为上一圈
		recBean.setCurrent(current-recBean.getTempTime());//现在秒数减去上圈秒表值，得出此圈秒数


		recBean.setSumTurn(Integer.parseInt(recBean.getSumTurn())+1+"");//总圈+1
		recBean.setSumDistance(Integer.parseInt(recBean.getSumDistance())+
				Integer.parseInt(HOST.RUN_DIS)+"");//总距离+单圈距离
		recBean.setTime(Integer.parseInt(recBean.getTime())+recBean.getCurrent()+"");//总用时+当圈用时

		recBean.setPace(Integer.parseInt(recBean.getSumDistance())
				/ Integer.parseInt(recBean.getTime())+"");// 总距离/用时      米/秒
		
		recBean.setTempTime(current);//最后记录秒表
		//本页面数据
		isContainsList(recBean);//是否包含，包含直接替换,不包含添加
		//刷新本页数据
		mAdapter.notifyDataSetChanged();
		recDao.addOrUpdate(recBean);//存入/更新 数据库
	}

	//是否包含，包含直接替换
	private void isContainsList(RunRecord recBean) {
		boolean b = false;
		for (RunRecord runRecord : recList) {
			if(runRecord.getEpc().equals(recBean.getEpc()))//同一个人
			{
				runRecord.setCurrent(recBean.getCurrent());
				runRecord.setLast(recBean.getLast());
				runRecord.setTempTime(recBean.getTempTime());
				b = true;
			}
		}
		if(!b) recList.add(recBean);
	}
	//是否包含，替换圈数
	private void isCurrent(RunRecord recBean) {
		for (RunRecord runRecord : recList) {
			if(runRecord.getEpc().equals(recBean.getEpc()))//同一个人
			{
				recBean.setCurrent(runRecord.getCurrent());
				recBean.setLast(runRecord.getLast());
				recBean.setTempTime(runRecord.getTempTime());
			}
		}
	}
}
