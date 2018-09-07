package github.example.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;


/** 不能调用~只能看
 * BottomNavigation底部导航栏
 * implementation 'com.ashokvarma.android:bottom-navigation-bar:2.0.4'
 */
public class BottomNavigation extends AppCompatActivity {/*
    BottomNavigationBar bottomNavigationBar;

    Fragment Fragment1, Fragment2, Fragment3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        *//*
          定义~
         bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
         Fragment1 = new FragmentHome();
         Fragment2 = new FragmentStar();
         Fragment3 = new FragmentOther();
         *//*

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//模式，一般就固定

        *//*增加tab,图片，文字*//*
        bottomNavigationBar
                //.addItem(new BottomNavigationItem(R.drawable.icon_1, "Home"))
                //.addItem(new BottomNavigationItem(R.drawable.icon_2, "Favorite"))
                //.addItem(new BottomNavigationItem(R.drawable.icon_3, "Local"))
                .setFirstSelectedPosition(0)
                .initialise();


        *//* 点击切换界面   R.id.home_activity_frag_container Fragment替换的容器， *//*
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            //  implements BottomNavigationBar.OnTabSelectedListener 亦可
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, Fragment1).commitAllowingStateLoss();
                        break;
                    case 1:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, Fragment2).commitAllowingStateLoss();
                        break;
                    case 2:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, Fragment3).commitAllowingStateLoss();
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

    }
*/

}
