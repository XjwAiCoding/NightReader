package com.example.pc.nightreader.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public  abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  abstract  void initView();
    public abstract  void  initData();
}
