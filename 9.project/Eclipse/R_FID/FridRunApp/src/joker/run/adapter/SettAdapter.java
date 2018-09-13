package joker.run.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import joker.run.sqliteorm.Epc;

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
      this.holder = new ViewHolder();
      convertView = LayoutInflater.from(this.context).inflate(
        2130903047, null);

      this.holder.ResultText1 = ((TextView)convertView.findViewById(2131361814));
      this.holder.ResultText2 = ((TextView)convertView.findViewById(2131361816));

      convertView.setTag(this.holder);
    } else {
      this.holder = ((ViewHolder)convertView.getTag());
    }

    this.holder.ResultText1.setText(((Epc)this.list.get(position)).getName());
    this.holder.ResultText2.setText(((Epc)this.list.get(position)).getEpc());

    return convertView;
  }

  class ViewHolder
  {
    TextView ResultText1;
    TextView ResultText2;

    ViewHolder()
    {
    }
  }
}