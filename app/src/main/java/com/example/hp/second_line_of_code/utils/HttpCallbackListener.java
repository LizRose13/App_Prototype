package com.example.hp.second_line_of_code.utils;

/**
 * 网络请求的回调接口：
 * 在静态方法中开启子线程无法返回服务器的数据，借助此方式返回
 * @author liz
 * @version V1.0
 * @date 2018/3/29
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
