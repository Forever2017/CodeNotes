package joker.run.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import joker.run.base.FragmentJoker;
import joker.run.data.ApplicationRunning;
import joker.run.data.HOST;
import joker.run.ui.R;
import joker.run.ui.TimeReckonActivity;

@SuppressLint("ValidFragment")
public class TimeFragment extends FragmentJoker implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    public TimeFragment(int Layout) {
        super(Layout);
    }

    private Spinner SpinnerRunJoker;
    private EditText TimeEditText;
    private Button TimeStartBut;

    @Override
    public void init() {
        super.init();

        SpinnerRunJoker = (Spinner) findViewById(R.id.SpinnerRunJoker);
        TimeEditText = (EditText) findViewById(R.id.TimeEditText);
        TimeStartBut = (Button) findViewById(R.id.TimeStartBut);

        SpinnerRunJoker.setOnItemSelectedListener(this);
        TimeStartBut.setOnClickListener(this);

        //
        HOST.RUN_TYPE = ApplicationRunning.getSharedInt("RUN_TYPE");
        HOST.RUN_DIS = ApplicationRunning.getSharedString("RUN_DIS");
        //
        SpinnerRunJoker.setSelection(HOST.RUN_TYPE);
        TimeEditText.setText(HOST.RUN_DIS);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //同时出发 or 分别出发
     /*   String[] languages = getResources().getStringArray(R.array.runs);
        Toast("你点击的是:" + languages[i]);*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.TimeStartBut:
                HOST.RUN_TYPE = SpinnerRunJoker.getSelectedItemPosition();
                HOST.RUN_DIS = TimeEditText.getText().toString();

                ApplicationRunning.setSharedInt("RUN_TYPE",HOST.RUN_TYPE);
                ApplicationRunning.setSharedString("RUN_DIS",HOST.RUN_DIS);

                startActivityForResult(new Intent(getActivity(), TimeReckonActivity.class),TimeReckonActivity.TIME_RECKON);
                break;

        }

    }

}
