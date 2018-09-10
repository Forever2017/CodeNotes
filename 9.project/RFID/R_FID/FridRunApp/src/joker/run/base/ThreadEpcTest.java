package joker.run.base;

import android.os.Handler;

public class ThreadEpcTest {
	String[] epcs = {"111111111111111111111111","222222222222222222222222","9999","333333333333333333333333","444444444444444444444444","9527"};
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
				if(id>5)  id = 0;
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
