package com.pvt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pvt.base.BaseViewHolder;
import com.pvt.bean.HomeBean;
import com.pvt.bean.RoomsBean;
import com.pvt.pvtvideo.ActivityRoomList;
import com.pvt.pvtvideo.ActivityRoomVideo;
import com.pvt.pvtvideo.R;

import java.util.List;

public class AdapterRoomList extends RecyclerView.Adapter {
    private List<RoomsBean.RoomBean> list;
    private Context context;

    public AdapterRoomList(List<RoomsBean.RoomBean> list, Context activity) {
        this.list = list;
        this.context = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, null);
        return new HalderViewHolder(rootView);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //在这部分做对UI的操作
        /*((HalderViewHolder) holder).iv.setImageResource(list.get(position).getHead());
        ((HalderViewHolder) holder).tv.setText(list.get(position).getName());                //由于RecycleView没有提供对item的点击事件，暂时是
        ((HalderViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ZhiBoActivity.class));
            }
        });*/


        ((HalderViewHolder) holder).title.setText(list.get(position).getNicename());

        ((HalderViewHolder) holder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // 进入房间
                Intent intent = new Intent(context, ActivityRoomVideo.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class HalderViewHolder extends BaseViewHolder {
        ImageView img;
        TextView title;

        public HalderViewHolder(View rootView) {
            super(rootView);

            img = $(R.id.RoomImg);
            title = $(R.id.RoomTitle);

        }
    }


}
