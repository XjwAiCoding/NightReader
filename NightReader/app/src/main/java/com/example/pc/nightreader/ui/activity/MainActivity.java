package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.db.base.CommonData;
import com.example.pc.nightreader.entity.TabEntity;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.ui.fragment.CareFragment;
import com.example.pc.nightreader.ui.fragment.PhotoFragment;
import com.example.pc.nightreader.ui.fragment.newsFragment;
import com.example.pc.nightreader.ui.fragment.videoFragment;
import com.example.pc.nightreader.widget.ViewFinder;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity  implements View.OnClickListener{

    CommonTabLayout mTabLayout;
    //底部导航栏标题
    private String[] titles = CommonData.titles;
    //底部导航栏未选中图片
    private int mIconUnSelect[] = CommonData.IconUnSelect;
    //底部导航栏选中图片
    private int mIconSelect[] = CommonData.IconSelect;

    private ArrayList<CustomTabEntity> mTabEntities;
    private newsFragment mNewsFragment;//新闻页面
    private PhotoFragment mPhotoFragment;//图片页面
    private videoFragment mVideoFragment;//视频页面
    private CareFragment mCareFragment;//设置页面
    private ImageView mBack;//返回键
    private  ImageView mDayOrNight;//日夜间模式切换
    boolean isOut=false;//是否退出
    private  boolean isClickNightChange=false;//是否点击了切换夜间模式
    private  Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            isOut=true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        registerListener();
        initFragment(savedInstanceState);
    }

    @Override
    public void initView() {
        mBack=ViewFinder.getView(this, R.id.back);
        mDayOrNight=ViewFinder.getView(this, R.id.DayOrNight);

        mTabLayout = ViewFinder.getView(this, R.id.tablayout);
        initTab();

    }

    @Override
    public void initData() {
        mTabEntities = new ArrayList<>();

    }
    /** 注册监听器 */
    private void registerListener()
    {
        mBack.setOnClickListener(this);
        mDayOrNight.setOnClickListener(this);

    }

    /**
     * 初始化fragment碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction _Transaction = getSupportFragmentManager().beginTransaction();
        int currentPosition = 0;
        if (savedInstanceState != null) {
            mNewsFragment = (newsFragment) getSupportFragmentManager().findFragmentByTag("newsFragment");
            mPhotoFragment = (PhotoFragment) getSupportFragmentManager().findFragmentByTag("photoFragment");
            mVideoFragment = (videoFragment) getSupportFragmentManager().findFragmentByTag("videoFragment");
            mCareFragment = (CareFragment) getSupportFragmentManager().findFragmentByTag("careFragment");
            //程序意外崩溃，再次启动回到保存的上次的位置
            currentPosition = savedInstanceState.getInt(CommonData.SAVEDINSTANCESTATE_KEY);

        } else {
            mNewsFragment = newsFragment.newInstance();
            mPhotoFragment = PhotoFragment.newInstance();
            mVideoFragment = videoFragment.newInstance();
            mCareFragment =  CareFragment.newInstance();
            _Transaction.add(R.id.commentContent, mNewsFragment, "newsFragment");
            _Transaction.add(R.id.commentContent, mPhotoFragment, "photoFragment");
            _Transaction.add(R.id.commentContent, mVideoFragment, "videoFragment");
            _Transaction.add(R.id.commentContent, mCareFragment, "careFragment");
        }
        _Transaction.commit();//提交事务
        switchToFragment(currentPosition);
        mTabLayout.setCurrentTab(currentPosition);
    }

    /**
     *  初始化底部导航页
     */
    private void initTab() {
        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new TabEntity(titles[i], mIconSelect[i], mIconUnSelect[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchToFragment(position);
            }

            @Override
            public void onTabReselect(int i) {

            }
        });
    }

    /**
     * 底部导航页 页面切换
     */
    private void switchToFragment(int position) {

        FragmentTransaction _FragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                //显示首页新闻
                _FragmentTransaction.show(mNewsFragment);
                _FragmentTransaction.hide(mPhotoFragment);
                _FragmentTransaction.hide(mVideoFragment);
                _FragmentTransaction.hide(mCareFragment);
                _FragmentTransaction.commitAllowingStateLoss();
                break;
            case 1:
                //显示图片栏
                _FragmentTransaction.show(mPhotoFragment);
                _FragmentTransaction.hide(mNewsFragment);
                _FragmentTransaction.hide(mVideoFragment);
                _FragmentTransaction.hide(mCareFragment);
                _FragmentTransaction.commitAllowingStateLoss();
                break;
            case 2:
                //显示视频栏
                _FragmentTransaction.show(mVideoFragment);
                _FragmentTransaction.hide(mPhotoFragment);
                _FragmentTransaction.hide(mNewsFragment);
                _FragmentTransaction.hide(mCareFragment);
                _FragmentTransaction.commitAllowingStateLoss();
                break;
            case 3:
                //显示关注栏
                _FragmentTransaction.show(mCareFragment);
                _FragmentTransaction.hide(mPhotoFragment);
                _FragmentTransaction.hide(mVideoFragment);
                _FragmentTransaction.hide(mNewsFragment);
                _FragmentTransaction.commitAllowingStateLoss();
                break;
        }
    }

    //跳转入口
    public static Intent getIntent(Context pContext) {
        Intent _Intent = new Intent(pContext, MainActivity.class);
        return _Intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //程序意外崩溃，保存的上次的位置
        if (mTabLayout != null) {
            outState.putInt(CommonData.SAVEDINSTANCESTATE_KEY, mTabLayout.getCurrentTab());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (isOut==true){
                finish();
            }else {
                isOut=false;
                //再按一次退出程序
                Toast.makeText(MainActivity.this, getString(R.string.tap_twice_to_exit),Toast.LENGTH_SHORT).show();
                new  Handler().postDelayed(mRunnable,1000);
            }
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        if (isOut==true){
            finish();
        }else {
            isOut=false;
            //再按一次退出程序
            Toast.makeText(MainActivity.this, getString(R.string.tap_twice_to_exit),Toast.LENGTH_SHORT).show();
            new  Handler().postDelayed(mRunnable,1000);
        }

        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                if (isOut==true){
                    finish();
                }else {
                    isOut=false;
                    //再按一次退出程序
                    Toast.makeText(MainActivity.this, getString(R.string.tap_twice_to_exit),Toast.LENGTH_SHORT).show();
                    new  Handler().postDelayed(mRunnable,1000);
                }
                break;
            case R.id.DayOrNight:
                Toast.makeText(MainActivity.this, "日夜间模式切换",Toast.LENGTH_SHORT).show();
                if (isClickNightChange==false){
                    mDayOrNight.setImageResource(R.mipmap.ic_action_button_theme_night);
                    isClickNightChange=true;
                }else {
                    mDayOrNight.setImageResource(R.mipmap.ic_action_button_theme_day);
                    isClickNightChange=false;
                }
                break;
        }
    }
}
