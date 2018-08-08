package com.pvt.pvtvideo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentOther extends Fragment{
    private View ACEW;
    private Activity activ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ACEW == null)
        {
            ACEW = inflater.inflate(R.layout.fragment_other, null);
            activ = getActivity();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) ACEW.getParent();
        if (parent != null)
        {
            parent.removeView(ACEW);
        }

//        init();

        return ACEW;
    }

}
