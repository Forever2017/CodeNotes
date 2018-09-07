package github.example.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import joker.kit.base.ActivityJoker;
/**   https://github.com/H07000223/FlycoTabLayout   */
public class FlycoTabLayout_GitHub extends ActivityJoker {

    /**

   1、FlycoTabLayout解决了每次切换都重新加载一次的问题


     */


    /*
     *  https://juejin.im/entry/58f70734a0bb9f006ab653b6
     *
    Fragment Fragment1, Fragment2, Fragment3;
    private void initView() {
        Fragment1 = new Fragment();
        Fragment2 = new Fragment();
        Fragment3 = new Fragment();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(Fragment1);
        fragments.add(Fragment2);
        fragments.add(Fragment3);

        SlidingTabLayout mTabLayout_1 = findViewById(R.id.stl);
        ViewPager id_viewpager = findViewById(R.id.id_viewpager);
        String[] titles = {"计时","结果","设置"};

        mTabLayout_1.setViewPager(id_viewpager,titles,this,fragments);

    }



     */


    /*  xml设置
     <com.flyco.tablayout.SlidingTabLayout
        android:id="@+id/stl"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:background="#EEEEEE"
        tl:tl_divider_color="#1A000000"
        tl:tl_divider_padding="13dp"
        tl:tl_divider_width="1dp"
        tl:tl_indicator_color="#000000"
        tl:tl_indicator_height="1.5dp"
        tl:tl_indicator_width_equal_title="true"
        tl:tl_tab_padding="22dp"
        tl:tl_tab_space_equal="true"
        tl:tl_textSelectColor="#000000"
        tl:tl_textUnselectColor="#66000000"
        tl:tl_underline_color="#1A000000"
        tl:tl_underline_height="1dp" />

    <android.support.v4.view.ViewPager
        android:id="@+id/id_viewpager"
        android:layout_width="fill_parent"
        android:layout_height="0dp"
        android:layout_weight="1"></android.support.v4.view.ViewPager>

        */

}
