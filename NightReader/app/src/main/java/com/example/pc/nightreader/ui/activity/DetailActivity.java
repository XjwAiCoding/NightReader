package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.nightreader.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }


    /** 入口 */
    public static  Intent getIntent(Context pContext){
        Intent _intent = new Intent(pContext, DetailActivity.class);
        return _intent;
    }
}
