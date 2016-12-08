package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.adapter.PageAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻fragment,由viewpager和tablayout组成
 */
public class newsFragment extends BaseFragment  implements View.OnClickListener{
    AppCompatActivity mActivity;
    View view;
    TabLayout mTabLayout;
    ViewPager mPager;
    ArrayList<String> mChannleList;
    ArrayList<Fragment> mFragmentList;
    String channle_name[];
    ImageView mExpend_arrow;
    public newsFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
            channle_name = getResources().getStringArray(R.array.news_channel_name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_news, container, false);
        initData();
        initView();
        return view;
    }

    @Override
    public void initData() {
        mChannleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < channle_name.length; i++) {
            mChannleList.add(channle_name[i]);
        }

    }

    @Override
    public void initView() {
        mTabLayout = ViewFinder.getView(view, R.id.tab_title);
        mPager = ViewFinder.getView(view, R.id.viewpager);
        mTabLayout.setupWithViewPager(mPager);
        initTab(channle_name.length, mTabLayout);
        mFragmentList.addAll(initFragment(channle_name.length));
        mPager.setOffscreenPageLimit(4);//设置viewpager缓存页数量
        PageAdapter _Adapter = new PageAdapter(getChildFragmentManager(), mChannleList, mFragmentList);
        mPager.setAdapter(_Adapter);
        mExpend_arrow=ViewFinder.getView(view, R.id.expandArrow);
        mExpend_arrow.setOnClickListener(this);
    }


    //添加tab页
    public void initTab(int count, TabLayout pTabLayout) {
        for (int i = 0; i < count; i++) {
            pTabLayout.addTab(pTabLayout.newTab());
        }
    }

    //添加fragment
    public List<Fragment> initFragment(int size) {
        List<Fragment> _fragment_list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            _fragment_list.add(NewsListFragment.newInstance(i));
        }
        return _fragment_list;
    }


    @Override
    public void onClick(View v) {
       // Toast.makeText(mActivity, "xjw", Toast.LENGTH_SHORT).show();
    }
}
