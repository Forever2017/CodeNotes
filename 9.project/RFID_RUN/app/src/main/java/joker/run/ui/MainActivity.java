package joker.run.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import joker.run.fragment.ResultFragment;
import joker.run.fragment.SettingFragment;
import joker.run.fragment.TimeFragment;
import joker.run.fragment.TimeReckonFragment;

//https://github.com/Ashok-Varma/BottomNavigation/wiki/Badges
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar bottomNavigationBar;
    Fragment Fragment1, Fragment2, Fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        Fragment1 = new TimeReckonFragment(R.layout.fragment_time_reckon);
//        Fragment1 = new TimeFragment(R.layout.fragment_time);
        Fragment2 = new ResultFragment(R.layout.fragment_result);
        Fragment3 = new SettingFragment(R.layout.fragment_setting);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//模式，一般就固定

        /*bottomNavigationBar
                .setActiveColor(R.color.cornflowerblue)//选中后文字颜色
                .setInActiveColor(R.color.gray)//未选中时 文字颜色

                .setBarBackgroundColor(R.color.gainsboro);//后面底纹颜色*/

        /*增加tab,图片，文字*/
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.test, "计时"))
                .addItem(new BottomNavigationItem(R.drawable.test, "结果"))
                .addItem(new BottomNavigationItem(R.drawable.test, "设置"))
                .setFirstSelectedPosition(0)
                .initialise();
/*


        bottomNavigationBar
                .addItem(
                        new BottomNavigationItem(R.drawable.test, "首页").
                                setInActiveColor(Color.BLACK)
                )

                .addItem(
                        new BottomNavigationItem(R.drawable.test, "附近").
                                setInActiveColor(Color.BLACK)
                )

                .addItem(
                        new BottomNavigationItem(R.drawable.test, "我的").
                                setInActiveColor(Color.BLACK)
                )


//                .setActiveColor(R.color.bottom_selected)
//                .setInActiveColor(R.color.bottom_unselected)
                .setFirstSelectedPosition(0)
                .initialise();
*/


        bottomNavigationBar.setTabSelectedListener(this);
        //默认到第一页
        onTabSelected(0);
    }


    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, Fragment1).commitAllowingStateLoss();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, Fragment2).commitAllowingStateLoss();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, Fragment3).commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
