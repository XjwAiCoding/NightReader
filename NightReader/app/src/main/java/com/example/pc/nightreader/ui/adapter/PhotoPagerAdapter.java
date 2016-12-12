package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;

import java.util.List;

/**
 * Created by xujiawei on 2016/12/12.
 */

public class PhotoPagerAdapter extends PagerAdapter {
   private   List<Photo> mPhotoList;
   private  Context mContext;


    public PhotoPagerAdapter(List<Photo> pPhotoList, Context pContext) {
        this.mPhotoList = pPhotoList;
        this.mContext = pContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = (View) LayoutInflater.from(mContext).inflate(R.layout.img_browse, null);
        //PhotoView img=(PhotoView)view.findViewById(R.id.photoview);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mPhotoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
