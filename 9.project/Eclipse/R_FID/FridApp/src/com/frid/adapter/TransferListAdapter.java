package com.frid.adapter;
import java.util.List;
import com.frid.fridapp.R;
import com.frid.pojo.GsonItem;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**单据adapter,单据列表上的Item*/
public class TransferListAdapter extends BaseAdapter{
	private List<GsonItem> list;
	private ViewHolder holder;
	private Context context;

	public TransferListAdapter(Context context, List<GsonItem> list){
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

	@SuppressLint("ResourceAsColor") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_transfer_list, null);
			
			holder.TransferListTitle = (TextView) convertView.findViewById(R.id.TransferListTitle);
			holder.TransferListState   = (TextView) convertView.findViewById(R.id.TransferListState);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		holder.TransferListTitle.setText(list.get(position).getName()+":"+list.get(position).getId());
		if(list.get(position).getState()==2){
			//已核实
			holder.TransferListState.setText(R.string.VerifyON);
			holder.TransferListState.setBackgroundResource(R.color.Green);
		}else{
			//未核实
			holder.TransferListState.setBackgroundResource(R.color.MainItemGrey);
			holder.TransferListState.setText(R.string.VerifyOFF);
		}
		
		return convertView;
	}

	class ViewHolder {
		TextView TransferListTitle,TransferListState;
	}



}
