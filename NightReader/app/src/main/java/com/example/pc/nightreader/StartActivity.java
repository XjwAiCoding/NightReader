package com.example.pc.nightreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.pc.nightreader.ui.activity.MainActivity;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.widget.ViewFinder;

public class StartActivity extends BaseActivity {

    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mImageView= ViewFinder.getView(this, R.id.logo);

    }

    @Override
    public void initData() {
        //跳转到MainActivity
        new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                Intent _Intent= MainActivity.getIntent(StartActivity.this);
                startActivity(_Intent);
                finish();
               // overridePendingTransition( 0,R.anim.slideout);

            }
        },2000);

    }

}
