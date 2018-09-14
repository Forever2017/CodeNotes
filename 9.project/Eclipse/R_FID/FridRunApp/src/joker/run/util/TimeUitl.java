package joker.run.util;

/**时间工具相关*/
public class TimeUitl {

   /**
    *  int转时间  1500秒转换成 00:00:00
    *  */
    public static String FormatHms(int time){
        String hh=time/3600>9?time/3600+"":"0"+time/3600;
        String mm=(time% 3600)/60>9?(time% 3600)/60+"":"0"+(time% 3600)/60;
        String ss=(time% 3600) % 60>9?(time% 3600) % 60+"":"0"+(time% 3600) % 60;
        return hh+":"+mm+":"+ss;
    }
    //未解决，只是隐藏了小时。。。。。。。。
    public static String FormatMs(int time) {
    	return String.format("%02d:%02d", time / 60 % 60, time % 60);
	}


}
