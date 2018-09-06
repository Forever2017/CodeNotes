package joker.run.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.lang.reflect.Field;

import joker.kit.base.FragmentJoker;
import joker.kit.view.button.SwitchButton;
import joker.run.ui.R;

@SuppressLint("ValidFragment")
public class SettingFragment extends FragmentJoker implements AdapterView.OnItemSelectedListener, joker.kit.view.button.SwitchButton.OnCheckedChangeListener {

    public SettingFragment(int Layout) {  super(Layout);  }

    private Spinner SpinnerJoker;
    private SwitchButton SwitchButton;

    @Override
    public void init() {
        super.init();

        SpinnerJoker = (Spinner) findViewById(R.id.SpinnerJoker);
        SwitchButton = (SwitchButton) findViewById(R.id.switch_button);

        SpinnerJoker.setOnItemSelectedListener(this);
        SwitchButton.setOnCheckedChangeListener(this);

        // SwitchButton.setChecked(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        try {

            Field field =       AdapterView.class.getDeclaredField("mOldSelectedPosition");
            field.setAccessible(true);  //设置mOldSelectedPosition可访问
            field.setInt(SpinnerJoker, AdapterView.INVALID_POSITION); //设置mOldSelectedPosition的值

        } catch (Exception e) {
            e.printStackTrace();
        }
        //中文 or 英语
        String[] languages = getResources().getStringArray(R.array.languages);
        Toast("你点击的是:"+languages[i]);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {

        if(isChecked) Toast("打开");
        else Toast("关闭");
    }
}
