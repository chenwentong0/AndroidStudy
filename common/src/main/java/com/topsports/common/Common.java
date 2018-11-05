package com.topsports.common;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Date 2018/10/17
 * Time 14:49
 *
 * @author wentong.chen
 */
public class Common {

    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
