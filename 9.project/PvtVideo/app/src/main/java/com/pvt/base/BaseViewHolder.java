package com.pvt.base;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public View rootView;

    public BaseViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
    }


    public <T extends View> T $(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }

    /**
     * 获取View
     *
     * @param resName 资源名称
     * @param <T>     具体的视图类型
     * @return
     */
    public <T extends View> T findViewByName(String resName) {
        return (T) rootView.findViewById(getViewId(resName));
    }

    /**
     * 获取view的ID (R.id.XXX)
     *
     * @param resName R.id.XXX中的XXX,字符串形式
     * @return (int)R.id.XXX
     */
    public int getViewId(String resName) {
        return rootView.getResources().getIdentifier(resName, "id", rootView.getContext().getPackageName());
    }
}

