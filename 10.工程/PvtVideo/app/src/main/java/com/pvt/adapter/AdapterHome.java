package com.pvt.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pvt.base.BaseViewHolder;
import com.pvt.bean.HomeBean;
import com.pvt.pvtvideo.ActivityRoomList;
import com.pvt.pvtvideo.R;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter {
    private List<HomeBean.ResultBean.FlatbedsBean> list;
    private Context context;

    public AdapterHome(List<HomeBean.ResultBean.FlatbedsBean> list, Context activity) {
        this.list = list;
        this.context = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        return new HalderViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((HalderViewHolder) holder).img.setImageURI(Uri.parse(list.get(position).getLogo()));

        ((HalderViewHolder) holder).title.setText(list.get(position).getName());

        ((HalderViewHolder) holder).StateNum.setText(list.get(position).getQuantity());

        ((HalderViewHolder) holder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!list.get(position).getQuantity().equals("0")) {
                    Intent intent = new Intent(context, ActivityRoomList.class);
                    intent.putExtra("id", list.get(position).getId());
                    context.startActivity(intent);
                } else
                    Toast.makeText(context, "平台没有房间.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class HalderViewHolder extends BaseViewHolder {
        SimpleDraweeView img;
        TextView title, StateNum;

        public HalderViewHolder(View rootView) {
            super(rootView);

            img = $(R.id.imageView);
            title = $(R.id.titleView);
            StateNum = $(R.id.StateNum);

        }
    }


}
