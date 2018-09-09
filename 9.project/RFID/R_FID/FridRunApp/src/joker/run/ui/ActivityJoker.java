package joker.run.ui;

import android.app.Activity;
import android.widget.Toast;

public class ActivityJoker extends Activity {

    /**Toast代码简洁*/
    public void Toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
