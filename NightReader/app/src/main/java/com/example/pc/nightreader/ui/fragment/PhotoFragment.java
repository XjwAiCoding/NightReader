package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.logic.async.AsyncPhoto;
import com.example.pc.nightreader.logic.listener.KLoadListener;
import com.example.pc.nightreader.ui.adapter.PhotoAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.List;

/**
 *
 */
public class PhotoFragment extends BaseFragment {
    private AppCompatActivity mActivity;//载体activity
    private View mRootView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private StaggeredGridLayoutManager mStaggeredGridManager;
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
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.gray);
        mRecyclerView.setHasFixedSize(true);//固定recyclerView的大小，被用于自身的优化
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置列表项的动画
        mStaggeredGridManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridManager);

    }

    @Override
    public void initData() {
        new AsyncPhoto(mActivity, new KLoadListener<List<Photo>>() {
            @Override
            public void onSuccess(List<Photo> pData) {
                PhotoAdapter _adapter=new PhotoAdapter(pData,mActivity);
                mRecyclerView.setAdapter(_adapter);
            }

            @Override
            public void onProgress(Integer... pProgress) {

            }

            @Override
            public void onFail(Exception pE) {

            }
        }).execute();


    }

}
