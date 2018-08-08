package com.pvt.pvtvideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class ActivityMain extends AppCompatActivity {

    Button MainRegistered;

    BottomNavigationBar bottomNavigationBar;

    Fragment FragmentHome;
    Fragment FragmentOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        FragmentHome = new FragmentHome();
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, FragmentOther).commitAllowingStateLoss();
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
    }
}











