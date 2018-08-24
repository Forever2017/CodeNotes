package asdemo.base;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File tey = new File(Environment.getExternalStorageDirectory()+"/sinyi/", "TopAgent.apk");
        File tey1 = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/sinyi/", "TopAgent.apk");

        tey.exists();

        Uri contentUri = FileProvider.getUriForFile(this, "asdemo.base.fileprovider", tey);
        Log.e("", "");
    }
}