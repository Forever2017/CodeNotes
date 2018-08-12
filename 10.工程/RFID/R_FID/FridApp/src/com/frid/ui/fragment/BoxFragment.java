package com.frid.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.frid.fridapp.R;
import com.frid.tool.ASHttp;
import com.frid.tool.ASHttp.AsyncHttp;
import com.frid.tool.SynchTool;
import com.frid.ui.Box;
import com.frid.ui.TransferList;
import com.frid.view.FRFragment;
/**保险箱*/
public class BoxFragment extends FRFragment implements OnClickListener{
	private SynchTool st;
	private Button BoxSynchronization;
	private ImageView BoxBoxButton,BoxTransferButton;
	public BoxFragment() { super(R.layout.fragment_box); }

	@Override
	public void init() {
		st = new SynchTool(getActivity());
		BoxBoxButton = (ImageView) findViewById(R.id.BoxBoxButton);
		BoxTransferButton = (ImageView) findViewById(R.id.BoxTransferButton);
		BoxSynchronization = (Button) findViewById(R.id.BoxSynchronization);
		BoxBoxButton.setOnClickListener(this);
		BoxTransferButton.setOnClickListener(this);
		BoxSynchronization.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.BoxBoxButton:
			startActivity(new Intent(getActivity(), Box.class));
			break;
		case R.id.BoxTransferButton:
			startActivity(new Intent(getActivity(), TransferList.class));
			break;
		case R.id.BoxSynchronization:
			st.upload();
			break;
		default:
			break;
		}
	}	

}
