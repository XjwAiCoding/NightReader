package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.AdviceActivity;
import com.example.pc.nightreader.ui.activity.CollectionActivity;
import com.example.pc.nightreader.ui.activity.NightSettingActivity;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.utils.file.DataCleanManager;
import com.example.pc.nightreader.widget.ViewFinder;

/**
 *设置页面
 */
public class CareFragment extends BaseFragment implements  View.OnClickListener{
    private View mRootView;
    /** 载体Activity，在onAttach()的时候给其赋值，在其他地方使用，代替getActivity(),防止在线程中使用报异常 */
   private  AppCompatActivity mActivity;
   private  LinearLayout mMyCollection;
   private  LinearLayout mModeChange;
   private  LinearLayout mCacheDelete;
   private  LinearLayout mAdvice;
   private LinearLayout mcheckUpdate;
   private TextView mCacheSize;

    public CareFragment() {

    }
    public static  CareFragment newInstance(){
        CareFragment _Fragment=new CareFragment();
        return  _Fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           mRootView= inflater.inflate(R.layout.fragment_care,container,false);
           initData();
           initView();
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
        }
    }

    @Override
    public void initView() {
        mMyCollection= ViewFinder.getView(mRootView, R.id.myCollection);
        mModeChange=ViewFinder.getView(mRootView, R.id.modeChange);
        mCacheDelete=ViewFinder.getView(mRootView, R.id.CacheDelete);
        mAdvice=ViewFinder.getView(mRootView, R.id.advice);
        mcheckUpdate=ViewFinder.getView(mRootView, R.id.checkUpdate);
        mCacheSize=ViewFinder.getView(mRootView, R.id.cacheSize);
        computeCacheSize();
        registerListener();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myCollection:
                Intent _collectionIntent= CollectionActivity.getIntent(mActivity);
                startActivity(_collectionIntent);
                break;
            case R.id.modeChange:
                Intent _nightIntent=NightSettingActivity.getIntent(mActivity);
                startActivity(_nightIntent);
                break;
            case R.id.CacheDelete:
                deleteCache();
                break;
            case R.id.advice:
                Intent _adviceIntent= AdviceActivity.getIntent(mActivity);
                startActivity(_adviceIntent);
                break;
            case R.id.checkUpdate:
                Toast.makeText(mActivity,"亲，这已是最新版了！",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**  注册监听器 */
    private  void registerListener(){
        mMyCollection.setOnClickListener(this);
        mModeChange.setOnClickListener(this);
        mCacheDelete.setOnClickListener(this);
        mAdvice.setOnClickListener(this);
        mcheckUpdate.setOnClickListener(this);
    }

     /** 删除缓存对话框*/
     public void deleteCache(){
        final AlertDialog.Builder _Dialog=new AlertDialog.Builder(mActivity);
        _Dialog.setTitle("确定删除？")
                .setMessage("清除缓存将会删除所有离线下载的新闻内容。")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteAllCache();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /** 计算缓存大小*/
  private   void   computeCacheSize(){
      new Thread(new Runnable() {
          @Override
          public void run() {
              mActivity.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          mCacheSize.setText(DataCleanManager.getTotalCacheSize(mActivity));
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
              });
          }
      }).start();

    }
    /** 删除所有缓存*/
    private void  deleteAllCache(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DataCleanManager.clearAllCache(mActivity);
                        mCacheSize.setText("0KB");
                    }
                });


            }
        }).start();
    }
}
