package com.example.pc.nightreader;

import android.app.Activity;
import android.app.Application;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhenbei on 2015/12/17.
 */
public class NewsApplication extends Application{

    /** NewsApplication */
    private static NewsApplication mApplication;
    /** ActivityList */
    private List<WeakReference<Activity>> mActivitys;


    @Override
    public void onCreate() {
        super.onCreate();
        //设置Application
        setApplication(this);
        //实例
        mActivitys = new ArrayList<WeakReference<Activity>>();
    }

    /**
     * 赋值
     *
     * @param pNewsApplication
     */
    private static void setApplication(NewsApplication pNewsApplication) {
        mApplication = pNewsApplication;
    }

    /**
     * 获取Application对象
     *
     * @return
     */
    public static NewsApplication getApplication() {
        return mApplication;
    }

    /**
     * 添加 Activity
     *
     * @param pActivity
     *            activity
     */
    public void addActivity(Activity pActivity) {
        mActivitys.add(new WeakReference<Activity>(pActivity));
    }

    /**
     * 获取 Activity List
     *
     * @return
     */
    public List<WeakReference<Activity>> getActivity() {
        return mActivitys;
    }

    /**
     * 关闭所有的Activity
     */
    public void removeAllActivity() {
        try {
            for (int i = mActivitys.size() - 1; i >= 0; i--) {
                if (mActivitys.get(i).get() != null) {
                    mActivitys.get(i).get().finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
