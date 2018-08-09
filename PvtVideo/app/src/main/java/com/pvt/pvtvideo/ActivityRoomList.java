package com.pvt.pvtvideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.db.bean.PvtDB;
import com.db.util.DButil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.pvt.adapter.AdapterRoomList;
import com.pvt.bean.CodeBean;
import com.pvt.bean.RoomsBean;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.pvt.util.ImageUtil;

import org.litepal.LitePal;

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

        params.put("token", DButil.PvtDBvaule("token"));
        params.put("userId",DButil.PvtDBvaule("userId"));

        HttpUtils.post(HttpUtils.ROOM_LIST, params, new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                RoomsBean hb =  GsonUtils.parseJson(responseString,RoomsBean.class);

                if(hb.getType().equals("true")){
                    if(hb.getResult()!=null&&hb.getResult().size()>0){
                        list.clear();
                        list.addAll(hb.getResult());
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),hb.getMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityRoomList.this,ActivityRegistered.class));
                }
            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        data();
    }
}
