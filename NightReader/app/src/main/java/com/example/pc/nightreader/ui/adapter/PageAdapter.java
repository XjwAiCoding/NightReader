package com.example.pc.nightreader.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/12/7.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> mStrings=new ArrayList<>();
    ArrayList<Fragment> mFragmentList=new ArrayList<>();


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    public PageAdapter(FragmentManager fm, ArrayList<String> mStrings, ArrayList<Fragment> mFragmentList) {
        super(fm);
        this.mStrings = mStrings;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings.get(position);
    }
}
