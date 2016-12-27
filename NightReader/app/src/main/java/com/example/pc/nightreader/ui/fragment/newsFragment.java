package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.adapter.PageAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.utils.AnimationUtil;
import com.example.pc.nightreader.widget.ViewFinder;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import static com.example.pc.nightreader.R.id.frame_column_order;

/**
 *  新闻fragment,由viewpager和tablayout组成
 */
public class newsFragment extends BaseFragment  implements View.OnClickListener{
    /** 载体Activity，在onAttach()的时候给其赋值，在其他地方使用，代替getActivity(),防止在线程中使用报异常 */
    AppCompatActivity mActivity;
    View view;
    TabLayout mTabLayout;//顶部导航栏
    ViewPager mPager;
    ArrayList<String> mMyChannleList;//我的频道名集合
    ArrayList<String> mMoreChanleList;//更多频道名集合
    ArrayList<Fragment> mFragmentList;
    String mMychannle_name[];//我的频道名数组
    String  mMorechannle_name[];
    ImageView mExpend_arrow;//旋转箭头
    FrameLayout mFrame_column_order;//盛装ColumnOrderFragment的视图
    ColumnOrderFragment mColumnOrderFragment;//栏目顺序定制fragment
    FrameLayout mFrameTablayout;//顶部导航栏父容器
    boolean isShowcolumn_order=false;//是否显示栏目顺序fragment

    public newsFragment() {}

    public  static  newsFragment newInstance(){
        newsFragment _Fragment=new newsFragment();
        return  _Fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
            mMychannle_name = getResources().getStringArray(R.array.news_Mychannel);
            mMorechannle_name=getResources().getStringArray(R.array.news_Morechannel);
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
        mMyChannleList = new ArrayList<>();
        mMoreChanleList=new ArrayList<>();
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < mMychannle_name.length; i++) {
            mMyChannleList.add(mMychannle_name[i]);
        }
        for (int i = 0; i < mMorechannle_name.length; i++) {
            mMoreChanleList.add(mMorechannle_name[i]);
        }

    }

    @Override
    public void initView() {
        mFrame_column_order=ViewFinder.getView(view, R.id.frame_column_order);
        mFrameTablayout=ViewFinder.getView(view, R.id.frameTablayout);
        mTabLayout = ViewFinder.getView(view, R.id.tab_title);
        mPager = ViewFinder.getView(view, R.id.viewpager);
        mTabLayout.setupWithViewPager(mPager);
        initTab(mMychannle_name.length, mTabLayout);
        mFragmentList.addAll(initFragment(mMychannle_name.length));
        mPager.setOffscreenPageLimit(4);//设置viewpager缓存页数量
        PageAdapter _Adapter = new PageAdapter(getChildFragmentManager(), mMyChannleList, mFragmentList);
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
        int _Vid = v.getId();
        if (_Vid == R.id.expandArrow){
            if (isShowcolumn_order){ //默认为false
                //隐藏栏目编辑拖拽页面ColumnOrderFragment，箭头发生向下旋转动画
                hideFragmentColumnOrder();

            }else {

                // 如果为true,显示栏目编辑拖拽页面ColumnOrderFragment，箭头发生向上旋转动画
                addFragmentColumnOrder();

            }
        }

    }

    /**
     * add FragmentColumnOrder :添加栏目顺序fragment
     */
    private void addFragmentColumnOrder(){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction _FragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();
                if (null == mColumnOrderFragment){
                     mColumnOrderFragment = ColumnOrderFragment.newInstance();
                    mColumnOrderFragment.setData(mMyChannleList,mMoreChanleList);//传递数据
                    _FragmentTransaction = _FragmentTransaction.add(frame_column_order, mColumnOrderFragment, ColumnOrderFragment.class.getSimpleName());
                }else {
                    _FragmentTransaction.show(mColumnOrderFragment);
                     showFragmentColumnOrder();
                }
                _FragmentTransaction.commit();
            }
        });

    }
    /**
     *  显示FragmentColumnOrder,方向从上往下
     */
    private void showFragmentColumnOrder() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 执行动画
                ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(frame_column_order, "translationY", -mFrame_column_order.getHeight(), 0f);
                _ObjectAnimator.setDuration(300);
                _ObjectAnimator.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // 栏目顺序fragment显示
                        mFrame_column_order.setVisibility(View.VISIBLE);
                       //隐藏标题栏
                        mFrameTablayout.setVisibility(View.GONE);
                        // 箭头旋转
                        AnimationUtil.rotation(mExpend_arrow, 0f, 180f);

                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {

                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        //显示栏目顺序fragment
                        isShowcolumn_order=true;

                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {

                    }
                });
                _ObjectAnimator.start();
            }
        });
    }

    /**
     * 隐藏FragmentColumnOrder，从下往上
     */
    public void hideFragmentColumnOrder() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 隐藏动画
                ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(frame_column_order, "translationY", 0f, -mFrame_column_order.getHeight());
                _ObjectAnimator.setDuration(300);
                _ObjectAnimator.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // 旋转
                        AnimationUtil.rotation(mExpend_arrow, 180f, 360f);

                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {

                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                       //显示标题栏
                        mFrameTablayout.setVisibility(View.VISIBLE);
                        // 隐藏栏目顺序fragment
                        mFrame_column_order.setVisibility(View.GONE);
                       //不显示栏目顺序fragment
                        isShowcolumn_order=false;


                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {

                    }
                });
                _ObjectAnimator.start();
            }
        });
    }
}
