package com.example.pc.nightreader.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class OkHttpUtils {

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();//获取okhttp对象实例
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS) //设置连接超时时间
                .writeTimeout(20, TimeUnit.SECONDS) //设置写数据超时时间
                .readTimeout(20, TimeUnit.SECONDS); //设置读数据超时时间
        mOkHttpClient=builder.build();

        //cookie enabled

        mDelivery = new Handler(Looper.getMainLooper());
    }

    //单例模式调用
    private synchronized static OkHttpUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }

    //get请求
    private void getRequest(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();//发起请求
        deliveryResult(callback, request);//返回结果
    }

    //post请求
    private void postRequest(String url, final ResultCallback callback, List<Param> params) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    //返回结果
    private void deliveryResult(final ResultCallback callback, Request request) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                sendFailCallback(callback, e);//请求失败回调
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                try {
                    String str = response.body().string();//请求成功返回的结果
                    if (callback.mType == String.class) {
                        sendSuccessCallBack(callback, str);
                    } else {

                    }
                } catch (final Exception e) {

                    sendFailCallback(callback, e);
                }
            }



        });
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    //添加post请求参数
    private Request buildPostRequest(String url, List<Param> params) {
        FormBody.Builder builder= new FormBody.Builder();
        for (Param param : params) {
            builder.add(param.key,param.value);
        }
        RequestBody requestBody = builder.build();

        return new Request.Builder().url(url).post(requestBody).build();
    }


/**********************对外接口************************/

    /**
     * get请求
     * @param url 请求url
     * @param callback 请求回调
     */
    public static void get(String url, ResultCallback callback) {
        getmInstance().getRequest(url, callback);
    }

    /**
     * post请求
     * @param url 请求url
     * @param callback 请求回调
     * @param params 请求参数
     */
    public static void post(String url, final ResultCallback callback, List<Param> params) {
        getmInstance().postRequest(url, callback, params);
    }

    /**
     * http请求回调类,回调方法在UI线程中执行
     * @param <T>
     */
    public static abstract class ResultCallback<T> {

        Type mType;

        public ResultCallback(){
            mType = getSuperclassTypeParameter(getClass());
        }

// 获得父类类型参数

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();//获得通用的超类
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         * @param e
         */
        public abstract void onFailure(Exception e);
    }

    /**
     * post请求参数类
     */
    public static class Param {

        static String key;
        static String value;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }

}
