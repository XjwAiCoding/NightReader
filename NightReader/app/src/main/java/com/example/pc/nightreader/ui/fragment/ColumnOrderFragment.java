package com.example.pc.nightreader.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.logic.ItemDragHelperCallback;
import com.example.pc.nightreader.ui.adapter.ColumnOrderAdapter;
import com.example.pc.nightreader.ui.fragment.base.BaseFragment;
import com.example.pc.nightreader.widget.ViewFinder;

import java.util.ArrayList;

/**
 *  栏目定制fragment
 */
public class ColumnOrderFragment extends BaseFragment {
     View mRootView;
     AppCompatActivity mActivity;//载体activity
     ArrayList<String> mMyChannleList=new ArrayList();
     ArrayList<String> mMoreChannleList=new ArrayList();
    private RecyclerView mRecyclerView;
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
        mRecyclerView= ViewFinder.getView(mRootView, R.id.channleRecycler);
        GridLayoutManager manager = new GridLayoutManager(mActivity, 4);//spanCount=4
        mRecyclerView.setLayoutManager(manager);
        ItemDragHelperCallback callback = new ItemDragHelperCallback();
         ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);//拖拽回调 绑定RecyclerView
        final ColumnOrderAdapter adapter = new ColumnOrderAdapter(mActivity, helper, mMyChannleList, mMoreChannleList);
        // 可变的span size 注：span size表示一个item的跨度，跨度了多少个span
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ColumnOrderAdapter.TYPE_MY || viewType == ColumnOrderAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ColumnOrderAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(mActivity, mMyChannleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

     public  void setData(ArrayList<String> pMyChannleList,ArrayList<String> pMoreChannleList){
         this.mMyChannleList=pMyChannleList;
         this.mMoreChannleList=pMoreChannleList;
    }
}
