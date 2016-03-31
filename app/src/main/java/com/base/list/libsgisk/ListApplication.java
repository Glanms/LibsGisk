package com.base.list.libsgisk;

import android.app.Application;

import com.base.list.libsgisk.tools.CrashHandler;
import com.base.list.libsgisk.tools.LogHelper;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ListApplication extends Application {


    // LOG 开关
    public static final boolean LOG_PRINT = true;
    private static ListApplication sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogHelper.w("App", "onLowMemory method");
    }

    public static ListApplication getInstance() {
        return sInstance;
    }
}
