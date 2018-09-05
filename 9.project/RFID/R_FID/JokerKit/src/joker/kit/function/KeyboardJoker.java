package joker.kit.function;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘相关操作方法
 */
public class KeyboardJoker {
    private InputMethodManager imm;
    private Context context;

    public KeyboardJoker(Context context) {
        this.context = context;
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 开关软键盘
     * 显示注意：1、view必须是VISIBLE的EditText，如果不是VISIBLE的，需要先将其设置为VISIBLE
     *           2、当前界面必须已经加载完成
     * 隐藏注意：1、view可以当前布局中已经存在的任何一个View，如果找不到可以用getWindow().getDecorView()
     */
    public void switchKeyboard(boolean boo, View view) {
        if (imm != null) {
            if (boo) {//显示软键盘
                view.requestFocus();
                imm.showSoftInput(view, 0);
            } else {//隐藏软键盘
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }



}
