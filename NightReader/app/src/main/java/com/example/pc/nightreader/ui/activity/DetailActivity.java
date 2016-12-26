package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.widget.ViewFinder;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class DetailActivity extends BaseActivity  implements View.OnClickListener {
    ImageView mImageView;
    TextView mTextView;
    News mNews;
    int position;
    ImageView mDetailBack;
    FloatingActionButton mDatailShare;//分享按钮
    WebView mNewsWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ShareSDK.initSDK(this,"1a3d0c9d3eaac");
        initData();
        initView();
    }

    @Override
    public void initView() {
        mDetailBack=ViewFinder.getView(this, R.id.detail_back);
        mDatailShare=ViewFinder.getView(this, R.id.detail_share);
        mNewsWeb= ViewFinder.getView(this, R.id.newsWeb);
        mDetailBack.setOnClickListener(this);
        mDatailShare.setOnClickListener(this);
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
            case R.id.detail_back:
                finish();
                break;
            case R.id.detail_share:
                showShare();
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
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
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
