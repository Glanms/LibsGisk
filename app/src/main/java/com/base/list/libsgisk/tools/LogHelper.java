package com.base.list.libsgisk.tools;

import android.util.Log;

import com.base.list.libsgisk.ListApplication;

/**
 * Created by Administrator on 2015/11/30.
 * 封装Log在Application中进行修改
 */
public class LogHelper {

    public static void print(String msg) {
        if (ListApplication.LOG_PRINT)
            System.out.println(msg);
    }

    public static void e(String tag, String msg) {
        if (ListApplication.LOG_PRINT)
            Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (ListApplication.LOG_PRINT)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (ListApplication.LOG_PRINT)
            Log.i(tag, tag);
    }

    public static void w(String tag, String msg) {
        if (ListApplication.LOG_PRINT)
            Log.w(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (ListApplication.LOG_PRINT)
            Log.v(tag, msg);
    }
}
