package com.example.pc.nightreader.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xujiawei on 2016/12/12.
 */

public class PhotoPagerAdapter extends PagerAdapter  {
   private   List<String> mUrlList=new ArrayList<>();
    private  AppCompatActivity mActivity;

    public PhotoPagerAdapter(List<String > pUrlList, AppCompatActivity  pActivity) {
        this.mUrlList = pUrlList;
        this.mActivity = pActivity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = (View) LayoutInflater.from(mActivity).inflate(R.layout.img_browse, null);
        PhotoView img=(PhotoView)view.findViewById(R.id.photoview);
        //图片加载
        ((ViewPager) container).addView(view);
        img.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {

            }

            @Override
            public void onOutsidePhotoTap() {
             mActivity.finish();
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return mUrlList.size();
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
