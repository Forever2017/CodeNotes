package joker.run.ui;

import android.os.Handler;

public class ThreadEpcTest {
	String[] epcs = {"1001","1002","9999","1003","1004","1005"};
	int id = 0;
	boolean is = true;
	EpcResult er;
	public ThreadEpcTest(final EpcResult er) {
		this.er = er;
		RunOK();
	}


	private void RunOK() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				if(id>5) id = 0;
				er.onResult(epcs[id]);
				id++;
				if(is) RunOK();
			} 
		}, 5000);
	}


	public void stop(){
		is = false;
	}

	public static abstract class EpcResult{
		public void onResult(String epc){};
	}


}
