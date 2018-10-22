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

/**单据adapter,单据列表上的Item*/
public class PListAdapter extends BaseAdapter{
	private List<GsonItem> list;
	private ViewHolder holder;
	private Context context;

	public PListAdapter(Context context, List<GsonItem> list){
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
					R.layout.item_list, null);

			holder.ItemContent = (TextView) convertView.findViewById(R.id.ItemListContent);
			holder.ItemState   = (TextView) convertView.findViewById(R.id.ItemListState);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		holder.ItemState.setVisibility(View.VISIBLE);
		holder.ItemContent.setText(list.get(position).getName());
		switch (list.get(position).getState()) {
		/**	0 入库
			1 出库
			2 正常盘点
			3 返库*/
		case 0:
			holder.ItemState.setText("入库");
			holder.ItemState.setTextColor(context.getResources().getColor(R.color.TitleColorMax));
			break;
		case 1:
			holder.ItemState.setText("出库");
			holder.ItemState.setTextColor(context.getResources().getColor(R.color.DeepPink));
			break;
		case 2:
			holder.ItemState.setText("盘点");
			holder.ItemState.setTextColor(context.getResources().getColor(R.color.Orange));
			break;
		default://3
			holder.ItemState.setText("返库");
			holder.ItemState.setTextColor(context.getResources().getColor(R.color.MainItemGrey));
			break;
		}
		return convertView;
	}

	class ViewHolder {
		TextView ItemContent,ItemState;
	} 



}
