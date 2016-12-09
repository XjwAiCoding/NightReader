package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Video;
import com.example.pc.nightreader.logic.async.AsyncVideo;
import com.example.pc.nightreader.logic.listener.KLoadListener;
import com.example.pc.nightreader.ui.adapter.VideoAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 *视频
 * */
public class videoFragment extends BaseFragment {
     private View mRootView;
     private AppCompatActivity mActivity;//载体activity
     private SwipeRefreshLayout mRefreshLayout;//下拉刷新组件
     private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;//视图管理器,与recyclerview搭配使用
    public videoFragment() {

    }

    public static videoFragment newInstance(){
        videoFragment _Fragment=new videoFragment();
        return  _Fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  AppCompatActivity){
            mActivity=(AppCompatActivity)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView=inflater.inflate(R.layout.fragment_video, container, false);
        initView();
        initData();
        return  mRootView;
    }

    @Override
    public void initView() {
        mRecyclerView= ViewFinder.getView(mRootView, R.id.videorecyclerView);
        mRefreshLayout=ViewFinder.getView(mRootView, R.id.videoRefreshLayout);
        //设置刷新时的动画颜色，可以设置4个
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.gray);
        mRecyclerView.setHasFixedSize(true);//固定recyclerView的大小，被用于自身的优化
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置列表项的动画
        mLinearLayoutManager=new LinearLayoutManager(mActivity);//获取视图管理器实例对象
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void initData() {
        new AsyncVideo(mActivity, new KLoadListener<List<Video>>() {
            @Override
            public void onSuccess(List<Video> pData) {
                VideoAdapter _Adapter=new VideoAdapter(mActivity,pData);
                 mRecyclerView.setAdapter(_Adapter);
            }

            @Override
            public void onProgress(Integer... pProgress) {

            }

            @Override
            public void onFail(Exception pE) {

            }
        }).execute();

    }

    @Override
    public void onPause() {
        super.onPause();
        //暂停播放视频
        JCVideoPlayer.releaseAllVideos();
    }
}
