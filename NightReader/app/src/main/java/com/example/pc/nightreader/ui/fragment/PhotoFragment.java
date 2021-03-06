package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.logic.async.AsyncPhoto;
import com.example.pc.nightreader.logic.listener.KLoadListener;
import com.example.pc.nightreader.logic.listener.OnItemClickListener;
import com.example.pc.nightreader.ui.activity.PhotoDetainActivity;
import com.example.pc.nightreader.ui.adapter.PhotoAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.ArrayList;
import java.util.List;


 /**
  * Created by xujiawei on 2016/12/12.
  * 图片
  */

public class PhotoFragment extends BaseFragment {
    private AppCompatActivity mActivity;//载体activity
    private View mRootView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private StaggeredGridLayoutManager mStaggeredGridManager;
    private ArrayList<String> mUrlList =new ArrayList<>();
    public PhotoFragment() {

    }
    public  static  PhotoFragment newInstance(){
        PhotoFragment _Fragment=new PhotoFragment();
        return  _Fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  AppCompatActivity){
            mActivity=(AppCompatActivity)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mRootView=inflater.inflate(R.layout.fragment_photo, container, false);

        initView();
        initData();
        return mRootView;
    }

    @Override
    public void initView() {
        mRecyclerView= ViewFinder.getView(mRootView, R.id.imagerecyclerView);
        mRefreshLayout=ViewFinder.getView(mRootView, R.id.imageRefreshLayout);
        //设置刷新时的动画颜色，可以设置4个
        mRefreshLayout.setColorSchemeResources(R.color.appbarColor);
        mRecyclerView.setHasFixedSize(true);//固定recyclerView的大小，被用于自身的优化
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置列表项的动画
        mStaggeredGridManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridManager);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新界面
                PhotoFragment.newInstance();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mRefreshLayout.setRefreshing(false);
                        showPopupWindow();
                    }
                }, 2000);
            }
        });
    }



    @Override
    public void initData() {
        new AsyncPhoto(mActivity, new KLoadListener<List<Photo>>() {
            @Override
            public void onSuccess(List<Photo> pData) {
                PhotoAdapter _adapter=new PhotoAdapter(pData,mActivity);
                mRecyclerView.setAdapter(_adapter);
                registerListener(_adapter,pData);
            }

            @Override
            public void onProgress(Integer... pProgress) {

            }

            @Override
            public void onFail(Exception pE) {

            }
        }).execute();

    }

    /**注册点击事件*/
    public  void registerListener(final PhotoAdapter pAdapter, final List<Photo> pData){
        pAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               //根据点击位置获取相应实体类并将url属性添加到mUrlList集合中
                 for (Photo _photo:pData){
                 mUrlList.add(_photo.getUrl());
                 }
                //跳转到图片详情activity
                 Intent intent = PhotoDetainActivity.getIntent(mActivity);
                 intent.putExtra("position",position);
                 intent.putStringArrayListExtra("urlList",mUrlList);
                 startActivity(intent);
            }
        });
    }

    /** 更新数据提示 */
    private void  showPopupWindow(){
        View _view=LayoutInflater.from(mActivity).inflate(R.layout.toastpopupwindow,null);
        final PopupWindow _ToastPopupWindow=new PopupWindow(_view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        _ToastPopupWindow.showAtLocation(mRootView, Gravity.TOP,0,220);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _ToastPopupWindow.dismiss();
            }
        },1000);

    }
}
