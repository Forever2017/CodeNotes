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
public class MListAdapter extends BaseAdapter{
	private List<GsonItem> list;
	private ViewHolder holder;
	private Context context;
	private String type;//显示文字类型..

	public MListAdapter(Context context, List<GsonItem> list){
		this.list = list;
		this.context = context;
	}
	public MListAdapter(Context context, List<GsonItem> list,String type){
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
					R.layout.item_list, null);

			holder.ItemContent = (TextView) convertView.findViewById(R.id.ItemListContent);
			holder.ItemState   = (TextView) convertView.findViewById(R.id.ItemListState);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		if(type!=null&&type.equals("query")){
			holder.ItemContent.setText(list.get(position).getName()+" | "+list.get(position).getId());
		}else
			holder.ItemContent.setText(list.get(position).getName());
		return convertView;
	}

	class ViewHolder {
		TextView ItemContent,ItemState;
	}



}
