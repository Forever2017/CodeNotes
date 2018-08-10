package com.pvt.pvtvideo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.bean.RoomBeanDB;
import com.pvt.adapter.AdapterRoomList;
import com.pvt.adapter.AdapterStar;
import com.pvt.bean.RoomsBean;
import com.pvt.view.SpacesItemDecoration;

import org.litepal.LitePal;

import java.util.List;

public class FragmentStar extends Fragment {
    private View ACEW;
    private Activity activ;

    private RecyclerView recyclerView;
    private AdapterStar adapter;
    private List<RoomBeanDB> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ACEW == null) {
            ACEW = inflater.inflate(R.layout.activity_room_list, null);
            activ = getActivity();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) ACEW.getParent();
        if (parent != null) {
            parent.removeView(ACEW);
        }

        init();

        return ACEW;
    }

    private void init() {

        recyclerView = ACEW.findViewById(R.id.RecycleRoomList);

        list = LitePal.findAll(RoomBeanDB.class);

        adapter = new AdapterStar(list, activ);



        //RecycleView 增加边距
        int spacingInPixels = 10;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));





        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(activ, 4)); //这里是设置为网格布局，后边4的参数是列数

    }


    //重新可见
    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(LitePal.findAll(RoomBeanDB.class));
        adapter.notifyDataSetChanged();
    }
}
