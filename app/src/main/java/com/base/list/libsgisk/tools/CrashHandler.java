package com.base.list.libsgisk.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Glanms on 16/3/31.
 * 用于收集应用Crash信息的类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final String PATH = Environment.getExternalStorageDirectory().
            getPath() + "/Libsgisk/log";
    private static final String FILE_HEADER = "crash";
    private static final String FILE_SUFFIX = ".trace";
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private static final boolean DEBUG = true;

    private static CrashHandler crashInstance = new CrashHandler();

    private Context mContext;


    public CrashHandler() {
        super();
    }

    public static CrashHandler getInstance() {
        return crashInstance;
    }

    // 初始化变量信息
    public void init(Context mcontext) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.mContext = mcontext.getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 程序出现异常在此处捕获并处理，收到的为非应用内捕获的异常
     *
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            dumpCrashInfoToSDCard(ex);
            uploadCrashInfoToServer();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mDefaultCrashHandler != null) {
            // 如果系统提供了异常处理，交给系统处理
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(Process.myPid());
        }

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                LogHelper.w(TAG, "SD card unmounted, skip dump crash.");
            }
        }

    }

    /**
     * 保存Crash信息到本地
     *
     * @param e
     */
    private void dumpCrashInfoToSDCard(Throwable e) {
//       String crash_desc = e.getMessage();
        File dir = new File(PATH);
        if (!dir.exists())
            dir.mkdir();
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(current));

        File format_file = new File(PATH + time + FILE_HEADER + FILE_SUFFIX);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(format_file)));
            pw.print(time);
//            pw.print(crash_desc);
            writePhoneInfo(pw);
            pw.println();
            e.printStackTrace(pw);
            pw.close();
        } catch (IOException e1) {
            LogHelper.e(TAG, "Dump Crash Info failed.");
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 写入手机信息到文件中
     *
     * @param pw
     */
    private void writePhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);

        //应用版本号
        pw.print("OS version");
        pw.print(pi.versionName);
        pw.print("_");
        pw.print(pi.versionCode);

        //手机制造商
        pw.print("Vendor:");
        pw.print(Build.MANUFACTURER);

        //手机型号
        pw.print("Model:");
        pw.print(Build.MODEL);

        //CPU架构
        pw.print("CPU ABI:");
        pw.print(Build.CPU_ABI);

    }

    /**
     * 上传统计信息到Server
     */
    private void uploadCrashInfoToServer() {
        LogHelper.d(TAG, "upload crash info to server.");
    }
}
