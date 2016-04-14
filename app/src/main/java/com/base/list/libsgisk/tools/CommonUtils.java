package com.base.list.libsgisk.tools;

import android.app.Activity;
import android.content.Intent;

import com.base.list.libsgisk.R;


/**
 * Created by Administrator on 2015/11/17.
 */
public class CommonUtils {

    public static void turnToActivity(Activity activity, Class<? extends Activity> acticityClass) {
        Intent intent = new Intent(activity, acticityClass);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
