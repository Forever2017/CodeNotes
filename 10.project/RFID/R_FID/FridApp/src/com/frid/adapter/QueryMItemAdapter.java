package com.frid.adapter;
import java.util.List;

import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**单据adapter,info , 单据详情上的Item*/
public class QueryMItemAdapter extends BaseAdapter{
	private List<GsonItem> list;
	private ViewHolder holder;
	private Context context;
	private Remove remove;

	public QueryMItemAdapter(Context context, List<GsonItem> list,Remove remove){
		this.list = list;
		this.context = context;
		this.remove = remove;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		holder.ItemContent.setText(list.get(position).getName());

		holder.ItemItemID.setText(list.get(position).getNumber().equals("1")?
				"暂未匹配":list.get(position).getNumber());

		if(list.get(position).getState() == 0){
			holder.ItemContent.setTextColor(context.getResources().getColor(R.color.MainItemGrey));
			holder.ItemItemID.setTextColor(context.getResources().getColor(R.color.MainItemGrey));
		}
		else{
			holder.ItemContent.setTextColor(context.getResources().getColor(R.color.Green));
			holder.ItemItemID.setTextColor(context.getResources().getColor(R.color.Green));
		}
		//if(gi.getState() == 1)
		holder.ItemState.setVisibility(list.get(position).getState()==1?View.VISIBLE:View.GONE);
		holder.ItemState.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Toast.makeText(context, "清除", 0).show();
				remove.RemoveResult(position);
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView ItemContent,ItemState,ItemItemID;
	}

	public interface Remove{
		public void RemoveResult(int i);
	}

}
