package com.frid.data;

import android.os.Handler;

public class ThreadEpcTest {
	String[] epcs = {"DF2018091600000000001969",
			"DF2018091600000000001975",
			"DF2018091600000000001991",
			"DF2018091600000000002016"
			,"9527",
			"1111111121111111111111111","2222222222222222222222222","99299","3333332333333333333333333","4444444444444444424444444","95227",
			"1111111111131111111111111","2222222222222222322222222","9999","3333333333333333333333333","44444444444444443344444444","933527"};
	int id = 0;
	boolean is = true;
	EpcResult er;
	public ThreadEpcTest(final EpcResult er) {
		this.er = er;
		RunOK();
	}


	public void RunOK() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if(id>10)  id = 0;
				er.onResult(epcs[id]);
				id++;
				if(is) RunOK();
			} 
		}, 500);
	}

	public void stop(){
		is = false;
	}

	public void cont(){
		is = true;
	}

	public static abstract class EpcResult{
		public void onResult(String epc){};
	}


}
