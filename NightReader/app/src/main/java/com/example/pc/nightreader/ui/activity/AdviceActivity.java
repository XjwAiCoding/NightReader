package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;

public class AdviceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /** 入口 */
    public  static Intent getIntent(Context pContext){
        Intent _Intent=new Intent(pContext,AdviceActivity.class);
        return  _Intent;
    }
}
