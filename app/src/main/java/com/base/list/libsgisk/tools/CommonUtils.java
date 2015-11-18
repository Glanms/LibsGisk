package com.base.list.libsgisk.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2015/11/17.
 */
public class CommonUtils {

    public static void turnToActivity(Context context,Class<? extends Activity> acticityClass){
        Intent intent = new Intent(context,acticityClass);
        context.startActivity(intent);
    }
}
