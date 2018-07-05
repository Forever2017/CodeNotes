package 7.小代码;

import android.os.Environment;
public class 查看是否有存储卡插入 {
	
	private void content() {
		String status=Environment.getExternalStorageState();
		if(status.equals(Environment.MEDIA_MOUNTED))
		{
			//说明有SD卡插入
		}
	}
}
