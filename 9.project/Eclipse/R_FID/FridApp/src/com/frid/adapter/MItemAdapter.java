package com.frid.adapter;
import java.util.List;

import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**单据adapter,info , 单据详情上的Item*/
public class MItemAdapter extends BaseAdapter{
	private List<GsonItem> list;
	private ViewHolder holder;
	private Context context;
	private int type;// 0 显示ID ，1显示EPC

	public MItemAdapter(Context context, List<GsonItem> list,int type){
		this.list = list;
		this.context = context;
		this.type = type;
	}

	@Override
	public int getCount() {
		return list.size();  
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);  
	}

	@Override
	public long getItemId(int position) {
		return position;  
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_item, null);

			holder.ItemContent = (TextView) convertView.findViewById(R.id.ItemItemContent);
			holder.ItemState   = (TextView) convertView.findViewById(R.id.ItemItemState);
			holder.ItemItemID   = (TextView) convertView.findViewById(R.id.ItemItemID);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		try {
			holder.ItemContent.setText(list.get(position).getName());
		} catch (Exception e) {
			Log.e("", "");
		}
		
		
		if(type==0) holder.ItemItemID.setText(list.get(position).getId());
		else holder.ItemItemID.setText(list.get(position).getNumber().equals("1")?"暂未匹配":list.get(position).getNumber());

		if(list.get(position).getState() == 0){
			holder.ItemContent.setTextColor(context.getResources().getColor(R.color.MainItemGrey));
			holder.ItemItemID.setTextColor(context.getResources().getColor(R.color.MainItemGrey));
		}
		else{
			holder.ItemContent.setTextColor(context.getResources().getColor(R.color.Green));
			holder.ItemItemID.setTextColor(context.getResources().getColor(R.color.Green));
		}
		return convertView;
	}

	class ViewHolder {
		TextView ItemContent,ItemState,ItemItemID;
	}



}
