package com.topsports.androidstudy.jsbridge;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Date 2018/9/30
 * Time 18:52
 *
 * @author wentong.chen
 */
public class MyJsBridge {
    public static final String TAG = MyJsBridge.class.getSimpleName();
    private final IJsBridge mIJsBridge;

    public MyJsBridge(IJsBridge iJsBridge) {
        mIJsBridge = iJsBridge;
    }

    @JavascriptInterface
    public void setValue(String value) {
        Log.d(TAG, value);
        mIJsBridge.setTextValue(value);
    }
}
