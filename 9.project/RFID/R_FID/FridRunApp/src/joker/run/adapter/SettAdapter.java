package joker.run.adapter;
import java.util.List;

import joker.run.sqliteorm.Epc;
import joker.run.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SettAdapter extends BaseAdapter{
	private List<Epc> list;
	private ViewHolder holder;
	private Context context;

	public SettAdapter(Context context, List<Epc> list){
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
					R.layout.item_set, null);

			holder.ResultText1 = (TextView) convertView.findViewById(R.id.ResultText1);
			holder.ResultText2 = (TextView) convertView.findViewById(R.id.ResultText2);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		
		holder.ResultText1.setText(list.get(position).getName());
		holder.ResultText2.setText(list.get(position).getEpc());
		
		return convertView;
	}

	class ViewHolder {
		TextView ResultText1,ResultText2;
	} 



}
