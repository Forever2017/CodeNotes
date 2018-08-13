package com.pvt.pvtvideo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.pvt.adapter.AdapterHome;
import com.pvt.bean.HomeBean;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.pvt.view.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private View ACEW;
    private Activity activ;

    private RecyclerView recyclerView;
    private AdapterHome adapter;
    private List<HomeBean.ResultBean.FlatbedsBean> list;
    private int i = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ACEW == null) {
            ACEW = inflater.inflate(R.layout.recycle_list_general, null);
            activ = getActivity();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) ACEW.getParent();
        if (parent != null) {
            parent.removeView(ACEW);
        }

        init();
        data();

        return ACEW;
    }

    public void init() {
        ((TextView)ACEW.findViewById(R.id.ListTitle)).setText(R.string.PvtHome);
        recyclerView = ACEW.findViewById(R.id.RecyclerList);

        list = new ArrayList<>();
        adapter = new AdapterHome(list, activ);
        recyclerView.setAdapter(adapter);        //和gridview一样直接setAdapter
        recyclerView.setLayoutManager(new GridLayoutManager(activ, 4)); //这里是设置为网格布局，后边3的参数是列数
        //RecycleView 增加边距
        if (i == 1) recyclerView.addItemDecoration(new GridItemDecoration(4, 15, true));

        i++;
    }


    public void data() {
        RequestParams params = new RequestParams();
        HttpUtils.post(HttpUtils.HOME_LIST, params, new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                HomeBean hb = GsonUtils.parseJson(responseString, HomeBean.class);

                list.clear();
                list.addAll(hb.getResult().getFlatbeds());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });


    }


}














