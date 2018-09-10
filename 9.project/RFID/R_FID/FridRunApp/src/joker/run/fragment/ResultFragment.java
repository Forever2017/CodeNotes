package joker.run.fragment;

import java.util.ArrayList;
import java.util.List;

import joker.run.adapter.ResultAdapter;
import joker.run.base.FragmentJoker;
import joker.run.sqliteorm.RunRecord;
import joker.run.sqliteorm.RunRecordDao;
import joker.run.ui.R;
import android.annotation.SuppressLint;
import android.widget.ListView;


@SuppressLint("ValidFragment")
public class ResultFragment extends FragmentJoker {

	public ResultFragment(int Layout) {  super(Layout);  }

	private static List<RunRecord> list = new ArrayList<RunRecord>();
	private static ResultAdapter mAdapter;
	private ListView ResultList;

	private static RunRecordDao rrd;
	@Override
	public void init() {
		super.init();

		ResultList = (ListView) findViewById(R.id.ResultList);
		list.clear();
		rrd = new RunRecordDao(getActivity());
		list.addAll(rrd.queryList());

		mAdapter =  new ResultAdapter(getActivity(), list);

		ResultList.setAdapter(mAdapter);

		mAdapter.notifyDataSetChanged();
	}

	public static void update() {
		list.clear();
		list.addAll(rrd.queryList());
		mAdapter.notifyDataSetChanged();
	}

}
