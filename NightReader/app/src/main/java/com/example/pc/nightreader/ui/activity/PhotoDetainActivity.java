package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.ui.adapter.PhotoPagerAdapter;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.List;

public class PhotoDetainActivity extends BaseActivity {

  private  ViewPager mPhotoPager;
  private int position;

   List<Photo> mPhotoList;
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
       mPhotoPager.setAdapter(new PhotoPagerAdapter(mPhotoList,this));
       mPhotoPager.setCurrentItem(position);
    }

    @Override
    public void initData() {
       position=getIntent(this).getIntExtra("position",0);
       mPhotoList=getIntent(this).getParcelableExtra("photoList");
    }

    /** 入口 */
    public  static  Intent getIntent(Context pContext){
        Intent _Intent=new Intent(pContext,PhotoDetainActivity.class);
        return  _Intent;
    }
    /** 回退按钮点击事件 （xml注册的） */
   public void  onback(View view){
       finish();
   }

}
