package com.topsports.mysocket;

import com.topsports.mysocket.request.Request;

/**
 * Date 2018/10/18
 * Time 11:59
 *
 * @author wentong.chen
 * socket请求接口定义
 */
public interface BizSocket {
    void sendRequest(Request request);
}
