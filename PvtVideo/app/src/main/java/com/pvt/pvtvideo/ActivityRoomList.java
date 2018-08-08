package com.pvt.pvtvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.db.core.Core;
import com.db.core.UserDB;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.pvt.adapter.AdapterRoomList;
import com.pvt.bean.RoomsBean;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ActivityRoomList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterRoomList adapter;
    private List<RoomsBean.RoomBean> list;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        id = getIntent().getStringExtra("id");
        init();
        data();
    }
    public void init(){
        recyclerView= findViewById(R.id.RecycleRoomList);

        list = new ArrayList<>();
        adapter=new AdapterRoomList(list,this);
        recyclerView.setAdapter(adapter);		//和gridview一样直接setAdapter
        recyclerView.setLayoutManager(new GridLayoutManager(this,3)); //这里是设置为网格布局，后边3的参数是列数

    }



    public void data(){
        // app=nice
        // livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b
        // token=0f8420f60e4a43ca9b4d60e59919f5a9
        // userId=161be15eeeea48f384ae3bba624f60d0
        RequestParams params = new RequestParams();
        params.put("app","nice");
        params.put("livePluginId",id);
        params.put("token", Core.userDb.getToken());
        params.put("userId",Core.userDb.getId());

        HttpUtils.post(HttpUtils.ROOM_LIST, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if(statusCode == 200){
                    RoomsBean hb =  GsonUtils.parseJson(responseString,RoomsBean.class);

                    list.clear();
                    list.addAll(hb.getResult());

                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

        });



    }
}
