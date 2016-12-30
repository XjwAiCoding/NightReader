package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.widget.ViewFinder;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class DetailActivity extends BaseActivity  implements View.OnClickListener {
    News mNews;
    int position;
    ImageView mBack;//返回按钮
    FloatingActionButton mDatailShare;//分享按钮
    WebView mNewsWeb;
    private  ImageView mDayOrNight;//日夜间模式切换
    private  boolean isClickNightChange=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ShareSDK.initSDK(this,"1a3d0c9d3eaac");//ShareSDK的appKey
        initData();
        initView();
        registerListener();
    }

    @Override
    public void initView() {
        mDayOrNight=ViewFinder.getView(this, R.id.DayOrNight);
        mBack=ViewFinder.getView(this, R.id.back);
        mDatailShare=ViewFinder.getView(this, R.id.detail_share);
        mNewsWeb= ViewFinder.getView(this, R.id.newsWeb);

        mNewsWeb.loadUrl(mNews.getUrl());
       //给webview设置内置浏览器，就不会打开手机系统浏览器
        mNewsWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    /** 注册监听器 */
    private void registerListener() {
        mDatailShare.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mDayOrNight.setOnClickListener(this);
    }

    @Override
    public void initData() {
       mNews= getIntent().getParcelableExtra("news");
       position=getIntent().getIntExtra("position",0);
    }


    /** 入口 */
    public static  Intent getIntent(Context pContext){
        Intent _intent = new Intent(pContext, DetailActivity.class);
        return _intent;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.detail_share:
                showShare();
                break;
            case R.id.DayOrNight:
                Toast.makeText(DetailActivity.this, "日夜间模式切换",Toast.LENGTH_SHORT).show();
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

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题
        oks.setTitle(mNews.getTitle());
        // titleUrl是标题的网络链接
        oks.setTitleUrl(mNews.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mNews.getDescription());
        // imagePath是图片的本地路径
        oks.setImagePath(mNews.getPicUrl());//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mNews.getUrl());
        // comment是我对这条分享的评论
        /*oks.setComment("我是测试评论文本");*/
        // site是分享此内容的网站名称
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址
        oks.setSiteUrl(mNews.getUrl());

      // 启动分享GUI
        oks.show(this);
    }


}
