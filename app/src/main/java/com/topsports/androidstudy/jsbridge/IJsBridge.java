package com.topsports.androidstudy.jsbridge;

/**
 * Date 2018/9/30
 * Time 18:52
 *
 * @author wentong.chen
 */
public interface IJsBridge {
    /**
     * 暴露给js调用 将js传的值显示在textview中
     * @param value
     */
    void setTextValue(String value);
}
