package com.baidu.bdpactioncloud;

import android.app.Application;
import android.util.DisplayMetrics;

import com.baidu.bdpactioncloud.common.TimberWrapper;

/**
 * BaseApp
 *
 * @author linjunwu
 * @since 2016/5/23
 */
public class BaseApp extends Application{

    private static final String TAG = "BaseApp";

    @Override
    public void onCreate() {
        super.onCreate();
        printScreenInfos();
//        Timber.plant(new Timber.DebugTree());
    }

    private void printScreenInfos() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        TimberWrapper.i(TAG, "DisplayMetrics:" + displayMetrics);
    }
}
