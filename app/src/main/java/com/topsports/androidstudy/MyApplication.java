package com.topsports.androidstudy;

import android.app.Application;

import com.topsports.common.Common;

/**
 * Date 2018/10/17
 * Time 14:50
 *
 * @author wentong.chen
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Common.init();
    }
}
