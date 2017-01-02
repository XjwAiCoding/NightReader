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
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.AdviceActivity;
import com.example.pc.nightreader.ui.activity.CollectionActivity;
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
   private  LinearLayout mLightChange;
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
        mLightChange=ViewFinder.getView(mRootView, R.id.lightChange);
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
            case R.id.lightChange:
                changeBrightness();
              /*  Intent _nightIntent=NightSettingActivity.getIntent(mActivity);
                startActivity(_nightIntent);*/
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
        mLightChange.setOnClickListener(this);
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
    /** 改变屏幕亮度  */
    private  void changeBrightness(){
        View _view=LayoutInflater.from(mActivity).inflate(R.layout.brightnesslayout,null);
        final SeekBar _seekbar=ViewFinder.getView(_view, R.id.seekbar);
        _seekbar.setProgress(40);
        _seekbar.setMax(100);
        _seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());
        final  AlertDialog.Builder _Builder=new AlertDialog.Builder(mActivity);
        _Builder.setMessage("左右拖动调节亮度").setView(_view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
         })  .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        _seekbar.setProgress(40);
                    }
                }).create().show();
    }

    /** 拖动条监听器 */
    private class OnSeekBarChangeListenerImpl implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
           setScreenBrightness((float)seekBar
                    .getProgress()/100);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /** 设置屏幕亮度  */
    private void setScreenBrightness(float num){
        WindowManager.LayoutParams layoutParams = mActivity
                .getWindow().getAttributes(); //取得屏幕的属性
        layoutParams.screenBrightness = num; //设置屏幕的亮度
        mActivity.getWindow().setAttributes(layoutParams); //重新设置窗口的属性

    }

}
