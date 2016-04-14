package com.base.list.libsgisk.tools;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Glanms on 16/4/14.
 * 文件操作类
 */
public class FileHelper {

    public FileHelper() {

    }

    private static final String TAG = "File-Helper";


    /**
     * 获取内部存储目录 /data/data/包名/files
     * 访问无需读写权限
     *
     * @param mContext
     * @return
     */
    public static String getInternalFilePath(Context mContext) {

        File eFile = mContext.getFilesDir();
        return eFile.getPath();

    }

    /**
     * 获取外部存储目录 /storage/emulated/0/Android/包名/files
     * 访问无需读写权限
     *
     * @param mContext
     * @return
     */
    public static String getExternalPrivateFilePath(Context mContext) {

        File eFile = null;
        boolean isExists = isExternalStorageWritable();
        if (isExists) {
            eFile = mContext.getExternalFilesDir(null);
        } else {
            eFile = mContext.getFilesDir();
        }
        return eFile.getPath();
    }

    /**
     * 获取外部存储目录 /storage/emulated/0/Libsgisk
     * 需要写权限创建，读取不需权限
     *
     * @param mContext
     * @return
     */
    public static String getExternalFilePath(Context mContext) {

        File eFile = null;
        boolean isExists = isExternalStorageWritable();
        if (isExists) {
            eFile = Environment.getExternalStoragePublicDirectory("Libsgisk");
        } else {
            eFile = mContext.getFilesDir();
        }
        return eFile.getPath();
    }

    /**
     * 获取外部存储（SD卡）根目录
     * 通过反射拿到,需要读写权限
     *
     * @param mContext
     * @param is_removed
     * @return
     */
    public static String getExternalSDFilePath(Context mContext, boolean is_removed) {
        StorageManager storageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(storageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (boolean) isRemovable.invoke(storageVolumeElement);
                if (removable == is_removed)
                    return path;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    /**
     * 测试方法，打印出相关文件路径
     */
    public static void printPath(Context mcontext) {


        LogHelper.i(TAG, "Environment.getExternalStorageDirectory:"
                + Environment.getExternalStorageDirectory().getPath());
        LogHelper.i(TAG, "Environment.getRootDirectory:"
                + Environment.getRootDirectory().getPath());
        LogHelper.i(TAG, "Environment.getDataDirectory:"
                + Environment.getDataDirectory().getPath());
        LogHelper.i(TAG, "Environment.getDownloadCacheDirectory:"
                + Environment.getDownloadCacheDirectory().getPath());

        LogHelper.i(TAG, "mContext.getPackageCodePath:"
                + mcontext.getPackageCodePath());
        LogHelper.i(TAG, "mContext.getPackageResourcePath:"
                + mcontext.getPackageResourcePath());
        LogHelper.i(TAG, "mContext.getExternalCacheDir:"
                + mcontext.getExternalCacheDir());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            LogHelper.i(TAG, "mContext.getExternalMediaDirs:"
                    + Arrays.toString(mcontext.getExternalMediaDirs()));
        LogHelper.i(TAG, "mContext.getExternalCacheDir:"
                + mcontext.getExternalFilesDir(null)); // 可以为空
        printSelectPath(mcontext, "context");
        printSelectPath(mcontext, "environment");

    }

    /**
     * 选择打印Context还是Environment对应的文件路径
     *
     * @param mcontext
     * @param sourceDir
     */
    private static void printSelectPath(Context mcontext, String sourceDir) {

        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_ALARMS);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_DCIM);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_DOCUMENTS);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_DOWNLOADS);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_MOVIES);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_MUSIC);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_NOTIFICATIONS);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_PODCASTS);
        printEnvironmentPath(mcontext, sourceDir, Environment.DIRECTORY_RINGTONES);

        printEnvironmentPath(mcontext, sourceDir, Environment.MEDIA_BAD_REMOVAL);
        printEnvironmentPath(mcontext, sourceDir, Environment.MEDIA_CHECKING);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            printEnvironmentPath(mcontext, sourceDir, Environment.MEDIA_EJECTING);
        printEnvironmentPath(mcontext, sourceDir, Environment.MEDIA_SHARED);
        printEnvironmentPath(mcontext, sourceDir, Environment.MEDIA_MOUNTED);
    }

    /**
     * 打印出Environment对应的文件路径
     *
     * @param sourceDir 来源目录{是Context 或者是 Environment}
     * @param path_type
     */
    private static void printEnvironmentPath(Context mcontext, String sourceDir, String path_type) {
        String source = null;
        if (sourceDir.equals("context")) {
            // 注意！！这里打印时会新建该文件夹
            source = "Context#getExternalFilesDir";
            try {
                LogHelper.i(TAG, source + "--" + path_type
                        + mcontext.getExternalFilesDir(path_type).getPath());
            } catch (NullPointerException e) {
                LogHelper.w(TAG, source + "--" + path_type + "不存在");
            }
        } else {
            source = "Environment.getExternalStoragePublicDirectory";
            try {
                LogHelper.i(TAG, source + "--" + path_type
                        + Environment.getExternalStoragePublicDirectory(path_type).getPath());
            } catch (NullPointerException e) {
                LogHelper.w(TAG, source + "--" + path_type + "不存在");
            }
        }
    }
}
