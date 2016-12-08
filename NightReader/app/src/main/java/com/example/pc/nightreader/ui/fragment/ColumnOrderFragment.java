package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;

/**
 *  栏目定制fragment
 */
public class ColumnOrderFragment extends BaseFragment {
    View mRootView;
    AppCompatActivity mActivity;//载体activity

    public ColumnOrderFragment() {
    }

    public  static  ColumnOrderFragment newInstance(){
        ColumnOrderFragment _ColumnOrderFragment=new ColumnOrderFragment();
        return _ColumnOrderFragment;
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
        mRootView=inflater.inflate(R.layout.fragment_column_order,container,false);
        initData();
        initView();
        return mRootView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}
