package joker.run.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import joker.kit.base.FragmentJoker;
import joker.run.ui.R;

@SuppressLint("ValidFragment")
public class TimeFragment extends FragmentJoker implements AdapterView.OnItemSelectedListener {

    public TimeFragment(int Layout) {
        super(Layout);
    }

    private Spinner SpinnerRunJoker;

    @Override
    public void init() {
        super.init();

        SpinnerRunJoker = (Spinner) findViewById(R.id.SpinnerRunJoker);
        SpinnerRunJoker.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //同时出发 or 分别出发
        String[] languages = getResources().getStringArray(R.array.runs);
        Toast("你点击的是:" + languages[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
