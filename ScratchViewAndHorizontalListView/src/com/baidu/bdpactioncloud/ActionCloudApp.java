package com.baidu.bdpactioncloud;

import android.app.Application;

import timber.log.Timber;

/**
 * ActionCloudApp
 *
 * @author linjunwu
 * @since 2016/5/23
 */
public class ActionCloudApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
