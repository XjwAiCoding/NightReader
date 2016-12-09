package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.logic.listener.OnItemClickListener;
import com.example.pc.nightreader.ui.adapter.viewholder.NewsItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/7/18.
 * recyclerView适配器
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    static   OnItemClickListener mOnItemClickListener;//点击事件接口
    private List<News> mData = new ArrayList<>();
    View mRootView;
    int mPosition;
    public NewsAdapter(Context context, List<News> pData) {
        this.mContext = context;
        mData.addAll(pData);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,final int viewType) {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        NewsItemViewHolder _holder = new NewsItemViewHolder(mRootView);
        _holder.setOnItemClickListener(new NewsItemViewHolder.OnItemClickListener() {
            @Override
            public void onItemClickListener(View itemView, int pPosition) {
                mOnItemClickListener.onItemClick(itemView,pPosition);
            }
        });
        /*if(mOnItemClickListener!=null){
           mRootView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mOnItemClickListener.onItemClick(view,mPosition);
               }
           });
       }*/
        return _holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewsItemViewHolder) {
             News mNews = mData.get(position);
            //加载图片和标题
            ((NewsItemViewHolder) holder).setTitle(mContext, mNews).setImage(mContext, mNews);
            /*if (mOnItemClickListener!=null){
                ((NewsItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v,position);
                    }
                });
            }*/
        }

    }

    @Override
    public int getItemViewType(int position) {
        mPosition=position;
        return position;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public News getItem( int position) {
        return mData == null ? null : mData.get(position);
    }


    /** 设置点击事件 */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


}



