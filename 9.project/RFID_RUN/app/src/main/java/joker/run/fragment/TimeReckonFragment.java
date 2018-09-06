package joker.run.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import joker.kit.base.FragmentJoker;
import joker.run.ui.R;

import static joker.kit.arithmetic.TimeUitl.FormatMiss;

@SuppressLint("ValidFragment")
public class TimeReckonFragment extends FragmentJoker implements Chronometer.OnChronometerTickListener,View.OnClickListener {

    public TimeReckonFragment(int Layout) {
        super(Layout);
    }

     private Chronometer ReckonTime;
    Button ReckonStop,ReckonResult;

    @Override
    public void init() {
        super.init();

        ReckonTime = (Chronometer) findViewById(R.id.ReckonTime);
        ReckonStop = (Button) findViewById(R.id.ReckonStop);
        ReckonResult = (Button) findViewById(R.id.ReckonResult);

        ReckonStop.setOnClickListener(this);
        ReckonResult.setOnClickListener(this);
        ReckonTime.setOnChronometerTickListener(this);
        ReckonTime.start();

    }

    int current = 0;
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        current++;
        System.out.println(current+"");
        chronometer.setText(FormatMiss(current));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ReckonStop://停止计时
                ReckonTime.stop();
                break;
            case R.id.ReckonResult://查看结果
                ReckonTime.start();
                break;


        }
    }
}
