package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.ui.adapter.PhotoPagerAdapter;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/12/12.
 */
public class PhotoDetainActivity extends BaseActivity {

   private  ViewPager mPhotoPager;
   private int position;
   private    List<String> mUrlList=new ArrayList<>();//存放图片url的集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detain);
        initData();
        initView();
    }

    @Override
    public void initView() {
       mPhotoPager= ViewFinder.getView(this, R.id.Photo_viewpager);
       mPhotoPager.setAdapter(new PhotoPagerAdapter(mUrlList,this));
       mPhotoPager.setCurrentItem(position);
    }

    @Override
    public void initData() {
       position=getIntent().getIntExtra("position",0);
       mUrlList=getIntent().getStringArrayListExtra("urlList");
    }

    /** 入口 */
    public  static  Intent getIntent(Context pContext){
        Intent _Intent=new Intent(pContext,PhotoDetainActivity.class);
        return  _Intent;
    }

    /** 回退按钮点击事件 （xml注册的） */
   public void  onBack(View view){
       finish();
   }

}
