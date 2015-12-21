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

    private static Toast toast;

    /**
     * Show short time notice using LENGTH_SHORT
     *
     * @param context
     * @param notice
     */
    public static void showShort(Context context, String notice) {
        if(null == toast)
        toast= Toast.makeText(context, notice, Toast.LENGTH_SHORT);
        else
        toast.setText(notice);
        toast.show();
    }

    /**
     * Show short time notice (@ResId) using LENGTH_SHORT
     *
     * @param context
     * @param notice
     */
    public static void showShort(Context context, int notice) {
        if(null == toast)
            toast= Toast.makeText(context, notice, Toast.LENGTH_SHORT);
        else
            toast.setText(notice);
        toast.show();
    }

    /**
     * Show long time notice using LENGTH_LONG
     *
     * @param context
     * @param notice
     */
    public static void showLong(Context context, String notice) {
        if(null == toast)
            toast= Toast.makeText(context, notice, Toast.LENGTH_LONG);
        else
            toast.setText(notice);
        toast.show();
    }

    /**
     * Show short time notice(@ResId) using LENGTH_SHORT
     *
     * @param context
     * @param notice
     */
    public static void showLong(Context context, int notice) {
        if(null == toast)
            toast= Toast.makeText(context, notice, Toast.LENGTH_LONG);
        else
            toast.setText(notice);
        toast.show();
    }

    /**
     * Custom duration and motice to show
     *
     * @param context
     * @param notice
     * @param duration
     */
    public static void showToastInfo(Context context, String notice, int duration) {
        if(null == toast)
            toast= Toast.makeText(context, notice, duration);
        else
            toast.setText(notice);
        toast.show();
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
        if(null == toast)
            toast= Toast.makeText(context, notice, duration);
        else
            toast.setText(notice);
        View toastView = LayoutInflater.from(context).inflate(ResLayout, null);
        toast.setGravity(Gravity.BOTTOM, 0, 15);
        toast.setView(toastView);
        toast.show();
    }

    /**
     * Hide the current Toast
     */
    public static void hideToast()
    {
        if (null != toast)
        {
            toast.cancel();
        }
    }

}
