package joker.kit.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ApplicationJoker extends Application {
    private static SharedPreferences sp;

     /**

        <application

            android:name="xxx.xxx.ApplicationJoker"

           // android:allowBackup="true"
          //  android:icon="@drawable/ic_launcher"
          //  android:label="@string/app_name"
          //  android:theme="@style/AppTheme" >


      */

    @Override
    public void onCreate() {
        super.onCreate();

        sp = getSharedPreferences("joker.application", Context.MODE_PRIVATE);

    }
    /**
     * SharedPreferences保存String类型数据
     * */
    public static void setSharedString(String name,String vaule){
        Editor ed = sp.edit();
        ed.putString(name, vaule);
        ed.commit();
    }
    /**
     * SharedPreferences保存Int类型数据
     * */
    public static void setSharedInt(String name,int vaule){
        Editor ed = sp.edit();
        ed.putInt(name, vaule);
        ed.commit();
    }

    /**
     * SharedPreferences取出String类型数据
     * */
    public static String getSharedString(String name){
        return sp.getString(name, null);
    }

    /**
     * SharedPreferences取出int类型数据
     * */
    public static int getSharedInt(String name){
        return sp.getInt(name, 0);
    }

}
