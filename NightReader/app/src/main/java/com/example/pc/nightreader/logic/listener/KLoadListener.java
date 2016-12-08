package com.example.pc.nightreader.logic.listener;

/**
 * Created by xujiawei on 2016/9/18.
 * 数据回调接口， 主要用于将异步任务后台获取的数据抛出
 */
public interface KLoadListener<T> {
    void onSuccess(T pData);
    void onProgress(Integer... pProgress);
    void onFail(Exception pE);
}
