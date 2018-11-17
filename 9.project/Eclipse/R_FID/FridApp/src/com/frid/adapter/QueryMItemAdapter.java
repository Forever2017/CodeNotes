package com.frid.adapter;
import java.util.List;

import com.frid.fridapp.R;
import com.frid.pojo.GsonItemCheck;
import com.frid.tool.FTool;

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
	private List<GsonItemCheck> list;
	private ViewHolder holder;
	private Context context;

	private DelItem di;

	public QueryMItemAdapter(Context context, List<GsonItemCheck> list,DelItem di){
		this.list = list;
		this.context = context;
		this.di = di;
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
			holder.ItemItemID   = (TextView) convertView.findViewById(R.id.ItemItemID);
			holder.ItemItemState   = (TextView) convertView.findViewById(R.id.ItemItemState);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		holder.ItemContent.setText(list.get(position).getName());

		holder.ItemItemID.setText(FTool.inteString(list.get(position).getEpc()));

		holder.ItemItemState.setVisibility(View.VISIBLE);
		holder.ItemItemState.setText("删除");
		holder.ItemItemState.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				di.onResult(list.get(position).getEpc(),list.get(position).getId());
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView ItemContent,ItemItemID,ItemItemState;
	}

	public static abstract class DelItem{
		public void onResult(String epc,String id){};
	}

}
