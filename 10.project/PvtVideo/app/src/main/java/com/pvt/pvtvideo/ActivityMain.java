package com.pvt.pvtvideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public class ActivityMain extends AppCompatActivity {

    Button MainRegistered;

    BottomNavigationBar bottomNavigationBar;

    Fragment FragmentHome,FragmentStar;
    Fragment FragmentOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        FragmentHome = new FragmentHome();
        FragmentStar = new FragmentStar();
        FragmentOther = new FragmentOther();

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "Favorite"))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "Local"))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Other"))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, FragmentHome).commitAllowingStateLoss();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, FragmentStar).commitAllowingStateLoss();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, FragmentOther).commitAllowingStateLoss();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, FragmentOther).commitAllowingStateLoss();
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, FragmentHome).commitAllowingStateLoss();

        MainRegistered = findViewById(R.id.MainRegistered);
        MainRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this,ActivityRegistered.class));
            }
        });


        //6.0和6.0以上，需要手动获取权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST);
                return;

            }
        }
    }


    private static final int REQUEST = 112;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(this, "应用程序不允许写在你的存储", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}











