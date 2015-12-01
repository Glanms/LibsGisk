package com.base.list.libsgisk.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/30.
 * Toast 工具类
 */
public class T {

    /**
     * Show short time notice using LENGTH_SHORT
     *
     * @param context
     * @param notice
     */
    public static void showShort(Context context, String notice) {
        Toast t = Toast.makeText(context, notice, Toast.LENGTH_SHORT);
        t.show();
    }

    /**
     * Show short time notice using LENGTH_SHORT
     *
     * @param context
     * @param notice
     */
    public static void showShort(Context context, int notice) {
        Toast t = Toast.makeText(context, notice, Toast.LENGTH_SHORT);
        t.show();
    }

    /**
     * Show long time notice using LENGTH_LONG
     *
     * @param context
     * @param notice
     */
    public static void showLong(Context context, String notice) {
        Toast t = Toast.makeText(context, notice, Toast.LENGTH_LONG);
        t.show();
    }

    /**
     * Show short time notice using LENGTH_SHORT
     *
     * @param context
     * @param notice
     */
    public static void showLong(Context context, int notice) {
        Toast t = Toast.makeText(context, notice, Toast.LENGTH_LONG);
        t.show();
    }

    /**
     * Custom duration and motice to show
     *
     * @param context
     * @param notice
     * @param duration
     */
    public static void showToastInfo(Context context, String notice, int duration) {
        Toast t = Toast.makeText(context, notice, duration);
        t.show();
    }

    /**
     * Custom toastView ,notice and duration to show
     *
     * @param ResLayout
     * @param context
     * @param notice
     * @param duration
     */
    public static void showCustomToast(int ResLayout, Context context, String notice, int duration) {
        Toast t = Toast.makeText(context, notice, duration);
        View toastView = LayoutInflater.from(context).inflate(ResLayout, null);
        t.setGravity(Gravity.BOTTOM, 0, 15);
        t.setView(toastView);
        t.show();
    }

}
