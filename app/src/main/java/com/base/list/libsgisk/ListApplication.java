package com.base.list.libsgisk;

import android.app.Application;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ListApplication extends Application {


    public static final boolean LOG_PRINT = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
