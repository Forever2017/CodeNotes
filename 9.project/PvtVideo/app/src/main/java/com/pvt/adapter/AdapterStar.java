package com.pvt.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.db.bean.RoomBeanDB;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pvt.base.BaseViewHolder;
import com.pvt.bean.RoomsBean;
import com.pvt.pvtvideo.R;
import com.video.ui.ActivityRoomVideo;

import java.util.List;

public class AdapterStar extends RecyclerView.Adapter {
    private List<RoomBeanDB> list;
    private Context context;

    public AdapterStar(List<RoomBeanDB> list, Context activity) {
        this.list = list;
        this.context = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_star, null);
        return new HalderViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((HalderViewHolder) holder).StarImg.setImageURI(Uri.parse(list.get(position).getImage()));

        ((HalderViewHolder) holder).StarTitle.setText(list.get(position).getNicename());

        ((HalderViewHolder) holder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 进入房间
                Intent intent = new Intent(context, ActivityRoomVideo.class);
                intent.putExtra("livePlatformId", list.get(position).getLivePlatformId());
                intent.putExtra("no", list.get(position).getNo());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class HalderViewHolder extends BaseViewHolder {
        SimpleDraweeView StarImg;
        TextView StarTitle;

        public HalderViewHolder(View rootView) {
            super(rootView);

            StarImg = $(R.id.StarImg);
            StarTitle = $(R.id.StarTitle);

        }
    }


}
