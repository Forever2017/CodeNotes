package com.frid.adapter;
import java.util.List;

import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**单据adapter,info , 单据详情上的Item*/
public class QueryTableMItemAdapter extends BaseAdapter{
	private List<GsonItem> list;
	private ViewHolder holder;
	private Context context;

	public QueryTableMItemAdapter(Context context, List<GsonItem> list){
		this.list = list;
		this.context = context;
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
					R.layout.item_table, null);

			holder.Name = (TextView) convertView.findViewById(R.id.TableItemName);
			holder.Now   = (TextView) convertView.findViewById(R.id.TableItemNow);
			holder.Sum   = (TextView) convertView.findViewById(R.id.TableItemSum);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		
		holder.Name.setText(list.get(position).getName());
		
		holder.Sum.setText(list.get(position).getNumber());
		
		holder.Now.setText("0");
		
		return convertView;
	}

	class ViewHolder {
		TextView Name,Now,Sum;
	}

}
