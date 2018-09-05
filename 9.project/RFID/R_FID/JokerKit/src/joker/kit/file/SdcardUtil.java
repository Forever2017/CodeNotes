package joker.kit.file;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有操作都在SD卡
 */
public class SdcardUtil {
/*
    写 SD 卡的权限：
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    创建/删除文件的权限：
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>

     判断是文件或文件夹
        File.isFile()
        File.isDirectory()
*/

    /**
     * 判断路径（文件夹）是否存在
     */
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取Sdcard路径
     * SD卡根目录  /storage/emulated/0
     */
    public static String sdFile() {
        /**          /storage/emulated/0         */
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取指定路径下所有文件夹（排除文件）
     * dirPath 路径
     */
    public static List<File> getDirectoryList(String dirPath) {
        File dir = new File(dirPath);

        //文件夹不存在
        if (!dir.exists()) return null;

        //取出文件列表 包含文件和文件夹
        File[] files = dir.listFiles();
        List<File> result = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) result.add(file);
        }
        return result;
    }

    /**
     * 获取指定路径下所有文件（排除文件夹）
     * dirPath 路径
     * type 文件类型   .txt   .doc   传空或者null获取全部（单单判断文件名）
     */
    public static List<File> getFileList(String dirPath, String type) {
        File dir = new File(dirPath);

        //文件夹不存在
        if (!dir.exists()) return null;

        //取出文件列表 包含文件和文件夹
        File[] files = dir.listFiles();
        List<File> result = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                if (type == null || type.equals("") || file.getName().contains(type))
                    result.add(file);
            }
        }
        return result;
    }

    /**
     * 获取指定路径下所有文件（文件+文件夹）
     * dirPath 路径
     * type 文件类型   .txt   .doc   传空或者null获取全部（单单判断文件名）
     */
    public static List<File> getSumFile(String dirPath) {
        File dir = new File(dirPath);

        //文件夹不存在
        if (!dir.exists()) return null;

        //取出文件列表 包含文件和文件夹
        File[] files = dir.listFiles();
        List<File> result = new ArrayList<>();
        for (File file : files) {
            result.add(file);
        }
        return result;
    }

    /**
     * 创建 文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static boolean createDir(String dirPath) {

        File dir = new File(dirPath);
        //文件夹是否已经存在
        if (dir.exists()) {
            //Log.w(TAG, "The directory [ " + dirPath + " ] has already exists");
            return true;
        }

        if (!dirPath.endsWith(File.separator)) {//不是以 路径分隔符 "/" 结束，则添加路径分隔符 "/"
            dirPath = dirPath + File.separator;
        }
        //创建文件夹
        if (dir.mkdirs()) {
            // Log.d(TAG, "create directory [ " + dirPath + " ] success");
            return true;
        }

        //Log.e(TAG, "create directory [ " + dirPath + " ] failed");
        return false;
    }
    /**
     * 创建 文件
     *
     * @param filePath 文件夹路径
     * @param name    文件名
     * @param type    文件后缀 （.txt .doc）
     */
    public static boolean CreateFile(String filePath,String name, String type) {
        File file = new File(filePath+"/"+name+type);

        //文件夹不存在
        if (file.exists()) return false;//文件已经存在了

        if (file.getPath().endsWith(File.separator)) {// 以 路径分隔符 结束，说明是文件夹
           // Log.e(TAG, "The file [ " + filePath + " ] can not be a directory");
            return false;
        }

        //判断父目录是否存在
        if (!file.getParentFile().exists()) {
            //父目录不存在 创建父目录
//            Log.d(TAG, "creating parent directory...");
            if (!file.getParentFile().mkdirs()) {
//                Log.e(TAG, "created parent directory failed.");
                return false;
            }
        }

        //创建目标文件
        try {
            if (file.createNewFile()) {//创建文件成功
//                Log.i(TAG, "create file [ " + filePath + " ] success");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
//            Log.e(TAG, "create file [ " + filePath + " ] failed");
            return false;
        }

        return false;
    }


}
