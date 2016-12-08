package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.logic.async.AsyncNews;
import com.example.pc.nightreader.logic.listener.KLoadListener;
import com.example.pc.nightreader.ui.adapter.NewsAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻列表fragment
 */
public class NewsListFragment extends BaseFragment {
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    View mRootView;
    AppCompatActivity mActivity;//载体activity
    private LinearLayoutManager mLinearLayoutManager;//视图管理器，// 与recyclerview搭配使用
    private int mPosition;
     ArrayList<News> mList;
    public NewsListFragment() {

    }

    public static NewsListFragment newInstance(int position) {
        NewsListFragment _NewsListFragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt("index", position);
        _NewsListFragment.setArguments(args);
        return _NewsListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initData();
        initView();
        return mRootView;
    }

    @Override
    public void initView() {
        mRefreshLayout = ViewFinder.getView(mRootView, R.id.RefreshLayout);
        mRecyclerView = ViewFinder.getView(mRootView, R.id.recyclerView);
        //设置刷新时的动画颜色，可以设置4个
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.gray);
        mRecyclerView.setHasFixedSize(true);//固定recyclerView的大小，被用于自身的优化
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置列表项的动画
        mLinearLayoutManager=new LinearLayoutManager(mActivity);//获取视图管理器实例对象
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
         //设置适配器
         mRecyclerView.setAdapter(new NewsAdapter(mActivity,mList));
    }

    @Override
    public void initData() {
       new AsyncNews(mActivity, new KLoadListener<List<News>>() {
           @Override
           public void onSuccess(List<News> pData) {
              mList=new ArrayList<>();
               mList.addAll(pData);

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
