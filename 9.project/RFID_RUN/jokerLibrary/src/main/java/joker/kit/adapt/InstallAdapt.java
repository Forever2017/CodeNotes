package joker.kit.adapt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * 安装APP的适配
 */
public class InstallAdapt {
    /**
     对于面向 Android 7.0 的应用，Android 框架执行的 StrictMode API 政策禁止在您的应用外部公开 file:// URI。
     如果一项包含文件 URI 的 intent 离开您的应用，则应用出现故障，并出现 FileUriExposedException 异常。
     要在应用间共享文件，您应发送一项 content:// URI，并授予 URI 临时访问权限。进行此授权的最简单方式是使用 FileProvider 类。
     利用FileProvider类进行授权：
     1、在清单文件中定义一个FileProvider
     <application
     ...>
         <provider
             android:name="android.support.v4.content.FileProvider"   固定
             android:authorities="包名.fileprovider"   包名+“.fileprovider”
             android:grantUriPermissions="true" 固定
             android:exported="false">  固定
                 <meta-data
                 android:name="android.support.FILE_PROVIDER_PATHS"  固定
                 android:resource="@xml/filepaths" />  根据自身文件路劲
         </provider>
     ...
     </application>

     2、设置共享文件的位置  res/xml/filepaths.xml
     <?xml version="1.0" encoding="utf-8"?>
     <paths>
             <external-path name="sinyi_joker" path="SINYI" />   path="." 时 为根目录，sinyi_joker是URI的虚拟路劲
             <!--物理路径相当于Context.getExternalFilesDir(String) + /SINYI/  更多的Google查-->
     </paths>
     3、安装实现代码..

     */


    /**
     * 安装APK，适配7.0+
     * apkFile   APK文件路径
     */
    public static void InstallApp(Context mContext, File apkFile) {
        String authority = mContext.getPackageName() + ".fileprovider"; //获取 android:authorities

        if (!apkFile.exists()) {
            System.out.println("apk不存在,file = " + apkFile.getPath());
            return;
        }

        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// 大于等于 7.0

            install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Uri contentUri = FileProvider.getUriForFile(mContext, authority, apkFile);

            install.setDataAndType(contentUri, "application/vnd.android.package-archive");

        } else {

            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");

        }

        mContext.startActivity(install);


    }


    /**
     * 获得file地址的uri,适配7.0
     */
    public static Uri getUriForFile(Context context, File file) {
        String authority = context.getPackageName() + ".fileprovider"; //获取 android:authorities
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// 大于等于 7.0

            fileUri = FileProvider.getUriForFile(context, authority, file);

        } else {

            fileUri = Uri.fromFile(file);

        }
        return fileUri;
    }

}






























