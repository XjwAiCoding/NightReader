package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;

public class NightSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_setting);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
    /** 入口 */
    public  static  Intent getIntent(Context pContext){
        Intent _Intent=new Intent(pContext,NightSettingActivity.class);
        return  _Intent;
    }
}
