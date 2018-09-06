package joker.kit.arithmetic;

public class TimeUitl {

   /**
    *  int转时间  1500秒转换成 00:00:00
    *  */
    public static String FormatMiss(int time){
        String hh=time/3600>9?time/3600+"":"0"+time/3600;
        String mm=(time% 3600)/60>9?(time% 3600)/60+"":"0"+(time% 3600)/60;
        String ss=(time% 3600) % 60>9?(time% 3600) % 60+"":"0"+(time% 3600) % 60;
        return hh+":"+mm+":"+ss;
    }


}
