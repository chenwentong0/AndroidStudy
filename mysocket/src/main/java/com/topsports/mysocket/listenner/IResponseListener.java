package com.topsports.mysocket.listenner;

import okio.ByteString;

/**
 * Date 2018/10/18
 * Time 12:02
 *
 * @author wentong.chen
 * 响应结果的监听
 */
public interface IResponseListener {
    /**
     * 成功的响应
     */
    void onSuccess(ByteString response);

    /**
     * 请求失败回调
     * @param t
     */
    void onFailure(Throwable t);
}
