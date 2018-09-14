package joker.run.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import joker.run.sqliteorm.Epc;
import joker.run.ui.R;

public class SettAdapter extends BaseAdapter
{
	private List<Epc> list;
	private ViewHolder holder;
	private Context context;

	public SettAdapter(Context context, List<Epc> list)
	{
		this.list = list;
		this.context = context;
	}

	public int getCount()
	{
		return this.list.size();
	}

	public Object getItem(int position)
	{
		return this.list.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_item, null);

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

	class ViewHolder{
		TextView ResultText1,ResultText2;
	}
}