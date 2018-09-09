package joker.run.adapter;
import java.util.List;

import joker.run.sqliteorm.RunRecord;
import joker.run.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**单据adapter,单据列表上的Item*/
public class TimeAdapter extends BaseAdapter{
	private List<RunRecord> list;
	private ViewHolder holder;
	private Context context;

	public TimeAdapter(Context context, List<RunRecord> list){
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
					R.layout.item_time, null);

			holder.ResultText1 = (TextView) convertView.findViewById(R.id.ResultText1);
			holder.ResultText2 = (TextView) convertView.findViewById(R.id.ResultText2);
			holder.ResultText3 = (TextView) convertView.findViewById(R.id.ResultText3);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		
		holder.ResultText1.setText(list.get(position).getName());
		holder.ResultText2.setText(list.get(position).getCurrent()+"");
		holder.ResultText3.setText(list.get(position).getLast()+"");
		
		return convertView;
	}

	class ViewHolder {
		TextView ResultText1,ResultText2,ResultText3;
	} 



}
