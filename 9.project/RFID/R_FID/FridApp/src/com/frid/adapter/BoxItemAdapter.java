package com.frid.adapter;
import java.util.List;
import com.frid.fridapp.R;
import com.frid.pojo.DBGsonProduct;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**单据adapter,info , 单据详情上的Item*/
public class BoxItemAdapter extends BaseAdapter{
	private List<DBGsonProduct> list;
	private ViewHolder holder;
	private Context context;

	public BoxItemAdapter(Context context, List<DBGsonProduct> list){
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
		holder.ItemContent.setText(list.get(position).getName());
		holder.ItemItemID.setText(list.get(position).getId());
		holder.ItemState.setVisibility(View.VISIBLE);

		/**=====商品状态====
			InStock = 2,   正常
			Showing = 5,   展示中
			Sold = 6,   已售出
			Abnormal = 7,  异常
			Lost = 8,   丢失
			LableBroken = 9   ID损坏*/
		switch (list.get(position).getState()) {
		case 2:
			holder.ItemState.setText("正常");
			holder.ItemState.setBackgroundResource(R.color.CheckBlue);
			break;
		case 5:
			holder.ItemState.setText("展示中");
			holder.ItemState.setBackgroundResource(R.color.Green);
			break;
		case 6:
			holder.ItemState.setText("已售");
			holder.ItemState.setBackgroundResource(R.color.MainItemGrey);
			break;
		case 7:
			holder.ItemState.setText("异常");
			holder.ItemState.setBackgroundResource(R.color.Red);
			break;
		case 8:
			holder.ItemState.setText("丢失");
			holder.ItemState.setBackgroundResource(R.color.Orange);
			break;
		case 9:
			holder.ItemState.setText("标签损毁");
			holder.ItemState.setBackgroundResource(R.color.DeepPink);
			break;

		default: //为0时，不增加任何颜色
			holder.ItemState.setText("正常");
			holder.ItemState.setBackgroundResource(R.color.CheckBlue);
			break;
		}

		if(list.get(position).getIsExist() == 0){
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
