package joker.run.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import joker.run.fragment.ResultFragment;
import joker.run.fragment.SettingFragment;
import joker.run.fragment.TimeFragment;

//https://github.com/Ashok-Varma/BottomNavigation/wiki/Badges
public class MainActivity extends AppCompatActivity {
    private Fragment Fragment1, Fragment2, Fragment3;
    private SlidingTabLayout mTabLayout_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        Fragment1 = new TimeFragment(R.layout.fragment_time);
        Fragment2 = new ResultFragment(R.layout.fragment_result);
        Fragment3 = new SettingFragment(R.layout.fragment_setting);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(Fragment1);
        fragments.add(Fragment2);
        fragments.add(Fragment3);

        mTabLayout_1 = findViewById(R.id.stl);
        ViewPager id_viewpager = findViewById(R.id.id_viewpager);
        String[] titles = {"计时", "结果", "设置"};
        mTabLayout_1.setViewPager(id_viewpager, titles, this, fragments);

    }

    //接收
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (TimeReckonActivity.TIME_RECKON == resultCode) {

            mTabLayout_1.setCurrentTab(1, false);

            ResultFragment.update();

        }
    }
}
